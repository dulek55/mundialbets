package com.mundial.mundialbets.Models;

import com.mundial.mundialbets.Entities.OddsEntity;
import lombok.Getter;

@Getter
public class OddsModel {
    Long gameId;
    Float homeWin;
    Float homeWinDraw;
    Float draw;
    Float awayWinDraw;
    Float awayWin;

    public void makeModel(OddsEntity oddsEntity) {
        this.gameId = oddsEntity.getGame().getId();
        this.homeWin = oddsEntity.getHomeTeamWin();
        this.homeWinDraw = oddsEntity.getHomeTeamWinOrDraw();
        this.draw = oddsEntity.getDraw();
        this.awayWinDraw = oddsEntity.getAwayTeamWinOrDraw();
        this.awayWin = oddsEntity.getAwayTeamWin();
    }
}


