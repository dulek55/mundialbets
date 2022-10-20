package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.MatchEntity;
import com.mundial.mundialbets.Entities.TeamEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/user")
public interface UserAPI {
    @PutMapping("/{id}")
    ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity);

    @PostMapping
    ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity);

    @GetMapping
    ResponseEntity<List<UserEntity>> getUser();

    @GetMapping("/{id}")
    ResponseEntity<UserEntity> getUserById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteUserById(@PathVariable Long id) throws Exception;
}
