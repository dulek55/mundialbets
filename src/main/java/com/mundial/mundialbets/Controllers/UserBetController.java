package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Entities.UserBetEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.UserBetModel;
import com.mundial.mundialbets.Services.GameService;
import com.mundial.mundialbets.Services.LeagueService;
import com.mundial.mundialbets.Services.UserBetService;
import com.mundial.mundialbets.Services.UserService;
import com.mundial.mundialbets.api.UserBetsAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserBetController implements UserBetsAPI {
    private final UserBetService userBetService;
    private final LeagueService leagueService;
    private final GameService gameService;
    private final UserService userService;
    public UserBetController (UserBetService userBetService, LeagueService leagueService, GameService gameService, UserService userService){
        this.userBetService = userBetService;
        this.leagueService = leagueService;
        this.gameService = gameService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserBetEntity> updateUserBets(Long id, UserBetEntity userBetEntity) throws Exception {
        UserBetEntity updateUserBet = userBetService.updateUserBet(id, userBetEntity);
        return ResponseEntity.ok(updateUserBet);
    }

    @Override
    public ResponseEntity<UserBetModel> addUserBets(UserBetModel userBetModel) throws Exception {
        //check if input data is ok
        if(userBetModel.getUserId() == null)
            throw new ApiRequestException("User ID not filled!");
        if(userBetModel.getLeagueId() == null)
            throw new ApiRequestException("League ID not filled!");
        if(userBetModel.getGameId() == null)
            throw new ApiRequestException("Game ID not filled!");
        if(userService.findById(userBetModel.getUserId()).isEmpty())
            throw new ApiRequestException("User not found!");
        if(leagueService.findById(userBetModel.getLeagueId()).isEmpty())
            throw new ApiRequestException("League not found!");
        if(gameService.findById(userBetModel.getGameId()).isEmpty())
            throw new ApiRequestException("Game not found!");
        if(userBetModel.getBet().isEmpty())
            throw new ApiRequestException("The bet is missing!");
        //create objects
        UserBetEntity userBetEntity = new UserBetEntity();
        Long gameId = userBetModel.getGameId();
        Long userId = userBetModel.getUserId();
        Long leagueId = userBetModel.getLeagueId();
        LeagueEntity leagueEntity = leagueService.getLeagueById(userBetModel.getLeagueId());
        UserEntity userEntity = userService.getUserById(userBetModel.getUserId());
        GameEntity gameEntity = gameService.getGameById(userBetModel.getGameId());
        //check if bet already exists
        if(userBetService.findUserBetByGameUserLeague(gameId, userId, leagueId) != null)
            userBetEntity = userBetService.findUserBetByGameUserLeague(gameId, userId, leagueId);
        else {
            userBetEntity.setUser(userEntity);
            userBetEntity.setLeague(leagueEntity);
            userBetEntity.setGame(gameEntity);
        }
        userBetEntity.setBet(userBetModel.getBet());    // set a bet everytime
        //save object
        userBetService.saveUserBet(userBetEntity);
        //prepare response
        UserBetModel response = new UserBetModel();
        response.makeModel(userBetEntity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserBetModel>> getUserBets(UserBetModel userBetModel) {
        //check if input data is ok
        if(userBetModel.getUserId() == null)
            throw new ApiRequestException("User ID not filled!");
        if(userService.findById(userBetModel.getUserId()).isEmpty())
            throw new ApiRequestException("User not found!");
        if(userBetModel.getGameId() != null)
            if(gameService.findById(userBetModel.getGameId()).isEmpty())
                throw new ApiRequestException("Game not found!");
        //create objects
        List<UserBetModel> userBetModels = new ArrayList<>();
        List<UserBetEntity> userBetEntities = new ArrayList<>();
        if(userBetModel.getGameId() == null)
            userBetEntities = userBetService.findUserBetsByUserId(userBetModel.getUserId());
        else
            userBetEntities = userBetService.findUserBetsByUserAndGame(userBetModel.getUserId(), userBetModel.getGameId());
        for (UserBetEntity userBetEntity : userBetEntities) {
            UserBetModel model = new UserBetModel();
            model.makeModel(userBetEntity);
            userBetModels.add(model);
        }
        return ResponseEntity.ok(userBetModels);
    }

    @Override
    public ResponseEntity<UserBetEntity> getUserBetsById(Long id) throws Exception {
        return ResponseEntity.ok(userBetService.getUserBetById(id));
    }

    @Override
    public ResponseEntity<?> deleteUserBetsById(Long id) throws Exception {
        userBetService.deleteUserBet(id);
        return null;
    }
}
