package com.mundial.mundialbets.Models;

import com.mundial.mundialbets.Entities.GameEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameModel {
    Long gameId;
    String homeTeamCode;
    String awayTeamCode;
    LocalDateTime dateTime;
    Integer homeScore;
    Integer awayScore;
    Integer homeScoreAfterOvertime;
    Integer awayScoreAfterOvertime;

    public void makeModel(GameEntity gameEntity) {
        this.gameId = gameEntity.getId();
        this.homeTeamCode = gameEntity.getHomeTeam().getCountryCode();
        this.awayTeamCode = gameEntity.getAwayTeam().getCountryCode();
        this.dateTime = gameEntity.getGameDate();
        this.homeScore = gameEntity.getHomeScore();
        this.awayScore = gameEntity.getAwayScore();
        this.homeScoreAfterOvertime = gameEntity.getHomeScoreAfterOvertime();
        this.awayScoreAfterOvertime = gameEntity.getAwayScoreAfterOvertime();
    }
}
