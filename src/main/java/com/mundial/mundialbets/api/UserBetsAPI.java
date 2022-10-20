package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.UserBetEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/userbet")
public interface UserBetsAPI {
    @PutMapping("/{id}")
    ResponseEntity<UserBetEntity> updateUserBets(@PathVariable Long id, @RequestBody UserBetEntity userBetEntity);

    @PostMapping
    ResponseEntity<UserBetEntity> addUserBets(@RequestBody UserBetEntity userBetEntity);

    @GetMapping
    ResponseEntity<List<UserBetEntity>> getUserBets();

    @GetMapping("/{id}")
    ResponseEntity<UserBetEntity> getUserBetsById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUserBetsById(@PathVariable Long id);
}
