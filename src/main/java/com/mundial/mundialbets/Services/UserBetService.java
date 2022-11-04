package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.UserBetEntity;
import com.mundial.mundialbets.Repositories.UserBetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBetService {
    private final UserBetRepository userBetRepository;

    public UserBetService(UserBetRepository userBetRepository) {
        this.userBetRepository = userBetRepository;
    }

    public UserBetEntity saveUserBet(UserBetEntity userBetEntity) {
        return userBetRepository.save(userBetEntity);
    }

    public UserBetEntity updateUserBet(Long id, UserBetEntity userBetEntity) throws Exception {
        userBetRepository.findById(id).orElseThrow(() -> new Exception("User Bet not found", new Error("USER BET NOT FOUND")));
        userBetEntity.setId(id);
        return userBetRepository.save(userBetEntity);
    }

    public List<UserBetEntity> getUserBets() {
        return userBetRepository.findAll();
    }

    public UserBetEntity getUserBetById(Long id) throws Exception {
        return userBetRepository.findById(id)
                .orElseThrow(() -> new Exception("User Bet id not found : " + id, new Error("USER BET NOT FOUND")));
    }

    public void deleteUserBet(Long id) throws Exception {
        userBetRepository.findById(id).orElseThrow(() -> new Exception("User Bet="+ id +" does not exist", new Error("USER BET NOT EXIST")));
        userBetRepository.deleteById(id);
    }

    public UserBetEntity findUserBetByGameUserLeague(Long gameId, Long userId, Long leagueId) {
        return userBetRepository.findUserBetByGameUserLeague(gameId, userId, leagueId);
    }

    public List<UserBetEntity> findUserBetsByUserId(Long userId) {
        return userBetRepository.findUserBetsByUserId(userId);
    }

    public List<UserBetEntity> findUserBetsByUserAndGame(Long userId, Long gameId) {
        return userBetRepository.findUserBetsByUserAndGame(userId, gameId);
    }
}
