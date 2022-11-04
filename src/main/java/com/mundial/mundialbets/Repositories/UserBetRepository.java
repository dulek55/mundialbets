package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.UserBetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserBetRepository extends JpaRepository<UserBetEntity, Long> {
    @Query(value = "SELECT * FROM user_bets WHERE game_id = :gameId and user_id = :userId and league_id = :leagueId",
    nativeQuery = true)
    UserBetEntity findUserBetByGameUserLeague(
            @Param("gameId") Long gameId,
            @Param("userId") Long userId,
            @Param("leagueId") Long leagueId);

    @Query(value = "SELECT * FROM user_bets WHERE user_id = :userId",
    nativeQuery = true)
    List<UserBetEntity> findUserBetsByUserId(
            @Param("userId") Long userId);

    @Query(value = "SELECT * FROM user_bets WHERE user_id = :userId and game_id = :gameId",
            nativeQuery = true)
    List<UserBetEntity> findUserBetsByUserAndGame(
            @Param("userId") Long userId,
            @Param("gameId") Long gameId);
}
