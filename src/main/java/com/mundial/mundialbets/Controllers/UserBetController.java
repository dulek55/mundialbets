package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.UserBetEntity;
import com.mundial.mundialbets.Services.UserBetService;
import com.mundial.mundialbets.api.UserBetsAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserBetController implements UserBetsAPI {
    private final UserBetService userBetService;
    public UserBetController (UserBetService userBetService){
        this.userBetService = userBetService;
    }

    @Override
    public ResponseEntity<UserBetEntity> updateUserBets(Long id, UserBetEntity userBetEntity) throws Exception {
        UserBetEntity updateUserBet = userBetService.updateUserBet(id, userBetEntity);
        return ResponseEntity.ok(updateUserBet);
    }

    @Override
    public ResponseEntity<UserBetEntity> addUserBets(UserBetEntity userBetEntity) {
        UserBetEntity updateUserBet = userBetService.saveUserBet(userBetEntity);
        return ResponseEntity.ok(updateUserBet);
    }

    @Override
    public ResponseEntity<List<UserBetEntity>> getUserBets() {
        return ResponseEntity.ok(userBetService.getUserBets());
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
