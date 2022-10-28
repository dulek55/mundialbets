package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Registration.Token.ConfirmationTokenEntity;
import com.mundial.mundialbets.Registration.Token.ConfirmationTokenService;
import com.mundial.mundialbets.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    public static final String USER_NOT_FOUND_MSG = "User %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).
                orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
    public String signUpUser(UserEntity user) {
        boolean userExist = userRepository.findUserByEmail(user.getEmail())
                .isPresent();
        if(userExist){
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public int enableUser (String email) {
        return userRepository.enableUser(email);
    }

    public UserEntity getUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("User id not found : " + id, new Error("User NOT FOUND")));
    }
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
