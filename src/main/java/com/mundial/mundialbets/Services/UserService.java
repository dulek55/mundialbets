package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public UserEntity updateUser(Long id, UserEntity userEntity) throws Exception {
        userRepository.findById(id).orElseThrow(() -> new Exception("User not found", new Error("USER NOT FOUND")));
        userEntity.setId(id);
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User id not found : " + id));
    }

    public void deleteUser(Long id) throws Exception {
        userRepository.findById(id).orElseThrow(() -> new Exception("User="+ id +" does not exist"));
        userRepository.deleteById(id);
    }
}
