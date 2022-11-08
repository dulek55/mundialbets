package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.*;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.LeagueModel;
import com.mundial.mundialbets.Models.LeagueResponseModel;
import com.mundial.mundialbets.Models.UserScoreModel;
import com.mundial.mundialbets.Services.LeagueService;
import com.mundial.mundialbets.Services.OddsService;
import com.mundial.mundialbets.Services.UserService;
import com.mundial.mundialbets.api.LeagueAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LeagueController implements LeagueAPI {
    private final LeagueService leagueService;
    private final UserService userService;
    private final OddsService oddsService;

    public LeagueController(LeagueService leagueService, UserService userService, OddsService oddsService) {
        this.leagueService = leagueService;
        this.userService = userService;
        this.oddsService = oddsService;
    }

    @Override
    public ResponseEntity<LeagueResponseModel> editLeague(LeagueModel leagueModel) throws Exception {
        //check if input data is ok
        if(leagueModel.getLeagueID() == null)
            throw new ApiRequestException("League ID is missing!");
        if(leagueService.findById(leagueModel.getLeagueID()).isEmpty())
            throw new ApiRequestException("League to edit not found!");
        //create object
        LeagueEntity leagueEntity = leagueService.getLeagueById(leagueModel.getLeagueID());
        //change it and save
        if(leagueModel.getName() != null)
            leagueEntity.setName(leagueModel.getName());
        if(leagueModel.getActive() != null) {
            if(leagueModel.getActive() > 0)
                leagueEntity.setActive(true);
            else
                leagueEntity.setActive(false);
        }
        leagueService.saveLeague(leagueEntity);
        //prepare response
        LeagueResponseModel leagueResponseModel = new LeagueResponseModel();
        leagueResponseModel.makeLeagueResponse(leagueEntity);
        return ResponseEntity.ok(leagueResponseModel);
    }

    public ResponseEntity<List<LeagueResponseModel>> getUsersLeagues(LeagueModel leagueModel) throws Exception {
        //check if input data is ok
        if(leagueModel.getUserID() == null)
            throw new ApiRequestException("User ID is missing!");
        if(userService.findById(leagueModel.getUserID()).isEmpty())
            throw new ApiRequestException("User not found!");
        //create object
        UserEntity userEntity = userService.getUserById(leagueModel.getUserID());
        List<LeagueResponseModel> leagueResponseModels = new ArrayList<>();
        for (LeagueEntity league : userEntity.getLeagues()) {
            LeagueResponseModel leagueResponseModel = new LeagueResponseModel();
            leagueResponseModel.makeLeagueResponse(league);
            leagueResponseModels.add(leagueResponseModel);
        }
        return ResponseEntity.ok(leagueResponseModels);
    }

    @Override
    public ResponseEntity<LeagueEntity> addLeague(LeagueEntity leagueEntity) {
        return ResponseEntity.ok(leagueService.saveLeague(leagueEntity));
    }

    @Override
    public ResponseEntity<LeagueResponseModel> joinLeague(LeagueModel leagueModel) throws Exception {
        //check if input data is ok
        if(leagueService.findById(leagueModel.getLeagueID()).isEmpty())
            throw new ApiRequestException("League to join not found!");
        if(userService.findById(leagueModel.getUserID()).isEmpty())
            throw new ApiRequestException("User not found!");
        //create objects
        LeagueEntity leagueEntity = leagueService.getLeagueById(leagueModel.getLeagueID());
        UserEntity userEntity = userService.getUserById(leagueModel.getUserID());
        //...and check again if data is ok
        if(leagueEntity.isUserInLeague(userEntity))
            throw new ApiRequestException("User already in this league!");
        //actually join league
        leagueEntity.addUser(userEntity);
        userEntity.addLeague(leagueEntity);
        leagueService.saveLeague(leagueEntity);
        userService.saveUser(userEntity);
        //prepare response
        LeagueResponseModel leagueResponseModel = new LeagueResponseModel();
        leagueResponseModel.makeLeagueResponse(leagueEntity);
        return ResponseEntity.ok(leagueResponseModel);
    }

    @Override
    public ResponseEntity<String> leaveLeague(LeagueModel leagueModel) throws Exception {
        //check if input data is ok
        if(leagueService.findById(leagueModel.getLeagueID()).isEmpty())
            throw new ApiRequestException("League to join not found!");
        if(userService.findById(leagueModel.getUserID()).isEmpty())
            throw new ApiRequestException("User not found!");
        //create objects
        LeagueEntity leagueEntity = leagueService.getLeagueById(leagueModel.getLeagueID());
        UserEntity userEntity = userService.getUserById(leagueModel.getUserID());
        //...and check again if data is ok
        if(!leagueEntity.isUserInLeague(userEntity))
            throw new ApiRequestException("User not in this league!");
        //actually leave league
        leagueEntity.removeUser(userEntity);
        userEntity.removeLeague(leagueEntity);
        leagueService.saveLeague(leagueEntity);
        userService.saveUser(userEntity);
        return ResponseEntity.ok("User id: " + leagueModel.getUserID().toString() + " removed from league ID: " + leagueModel.getLeagueID().toString());
    }

    @Override
    public ResponseEntity<List<LeagueResponseModel>> getLeague() {
        List<LeagueResponseModel> leagueResponseModels = new ArrayList<>();
        for (LeagueEntity league : leagueService.getLeagues()) {
            LeagueResponseModel leagueResponseModel = new LeagueResponseModel();
            leagueResponseModel.makeLeagueResponse(league);
            leagueResponseModels.add(leagueResponseModel);
        }
        return ResponseEntity.ok(leagueResponseModels);
    }

    @Override
    public ResponseEntity<LeagueEntity> getLeagueById(Long id) throws Exception {
        return ResponseEntity.ok(leagueService.getLeagueById(id));
    }

    @Override
    public ResponseEntity<?> deleteLeagueById(Long id) throws Exception {
        leagueService.deleteLeague(id);
        return null;
    }

    @Override
    public ResponseEntity<List<UserScoreModel>> getUsersScore(LeagueModel leagueModel) throws Exception {
        Long leagueID = leagueModel.getLeagueID();
        //check if input data is ok
        if(leagueModel.getLeagueID() == null)
            throw new ApiRequestException("League ID is missing!");
        if(leagueService.findById(leagueID).isEmpty())
            throw new ApiRequestException("League not found!");
        //create objects
        LeagueEntity league = leagueService.getLeagueById(leagueID);
        List<UserScoreModel> userScoreModels = new ArrayList<>();
        Float score = 0f;
        for (UserEntity user : league.getUsers()) {
            UserScoreModel userScoreModel = new UserScoreModel();
            userScoreModel.setScore(getUserScore(league, user));
            userScoreModel.setUserId(user.getId());
            userScoreModels.add(userScoreModel);
        }
        //prepare response
        return ResponseEntity.ok(userScoreModels);
    }

    public Float getUserScore(LeagueEntity leagueEntity, UserEntity userEntity) {
        Float result = 0f;
        List<UserBetEntity> userBets = leagueEntity.getUserBets();
        GameEntity gameEntity = new GameEntity();
        OddsEntity oddsEntity = new OddsEntity();
        String bet = new String();
        boolean goodBet = false;
        Integer homeScore, awayScore;
        for (UserBetEntity userBet : userBets) {
            //check if bet is good
            goodBet = false;
            gameEntity = userBet.getGame();
            bet = userBet.getBet();
            oddsEntity = oddsService.findOddsByGameId(gameEntity.getId());
            homeScore = gameEntity.getHomeScore();
            awayScore = gameEntity.getAwayScore();
            if(bet.contains("1"))
                if(homeScore > awayScore)
                    goodBet = true;
            if(bet.contains("X"))
                if(homeScore == awayScore)
                    goodBet = true;
            if(bet.contains("2"))
                if(homeScore < awayScore)
                    goodBet = true;
            //add points
            if(goodBet) {
                switch (bet) {
                    case "1":
                        result += oddsEntity.getHomeTeamWin();
                        break;
                    case "1X":
                        result += oddsEntity.getHomeTeamWinOrDraw();
                        break;
                    case "X":
                        result += oddsEntity.getDraw();
                        break;
                    case "X2":
                        result += oddsEntity.getAwayTeamWinOrDraw();
                        break;
                    case "2":
                        result += oddsEntity.getAwayTeamWin();
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }
}
