package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Services.UserService;
import com.mundial.mundialbets.api.UserAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserAPI {

    private final UserService userService;
    public UserController (UserService userService){
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserEntity> updateUser(Long id, UserEntity userEntity) throws Exception {
        UserEntity updateUser = userService.updateUser(id, userEntity);
        return ResponseEntity.ok(updateUser);
    }

    @Override
    public ResponseEntity<UserEntity> addUser(UserEntity userEntity) {
        UserEntity user = userService.saveUser(userEntity);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserEntity>> getUser() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    public ResponseEntity<UserEntity> getUserById(Long id) throws Exception {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Override
    public ResponseEntity<?> deleteUserById(Long id) throws Exception {
        userService.deleteUser(id);
        return null;
    }
}
