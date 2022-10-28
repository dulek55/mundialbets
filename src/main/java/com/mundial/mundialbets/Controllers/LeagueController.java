package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.GameModel;
import com.mundial.mundialbets.Models.LeagueModel;
import com.mundial.mundialbets.Models.LeagueResponseModel;
import com.mundial.mundialbets.Services.LeagueService;
import com.mundial.mundialbets.Services.UserService;
import com.mundial.mundialbets.api.LeagueAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LeagueController implements LeagueAPI {
    private final LeagueService leagueService;
    private final UserService userService;

    public LeagueController(LeagueService leagueService, UserService userService) {
        this.leagueService = leagueService;
        this.userService = userService;
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
        LeagueResponseModel leagueResponseModel = new LeagueResponseModel();
        for (LeagueEntity league : leagueService.getLeagues()) {
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
}
