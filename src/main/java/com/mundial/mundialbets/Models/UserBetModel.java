package com.mundial.mundialbets.Models;

import com.mundial.mundialbets.Entities.UserBetEntity;
import lombok.Getter;

@Getter
public class UserBetModel {
    Long userId;
    Long gameId;
    Long leagueId;
    String bet; // to be changed?

    public void makeModel(UserBetEntity userBetEntity) {
        this.userId = userBetEntity.getUser().getId();
        this.gameId = userBetEntity.getGame().getId();
        this.leagueId = userBetEntity.getLeague().getId();
        this.bet = userBetEntity.getBet();
    }
}
