package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.OddsEntity;
import com.mundial.mundialbets.Entities.UserBetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OddsRepository extends JpaRepository<OddsEntity, Long> {
    @Query(value = "SELECT * FROM odds WHERE game_id = :gameId",
            nativeQuery = true)
    OddsEntity findOddsByGameId(
            @Param("gameId") Long gameId);
}
