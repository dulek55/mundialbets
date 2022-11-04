package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.OddsEntity;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.OddsModel;
import com.mundial.mundialbets.Services.GameService;
import com.mundial.mundialbets.Services.OddsService;
import com.mundial.mundialbets.api.OddsAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OddsController implements OddsAPI {
    private final OddsService oddsService;
    private final GameService gameService;

    public OddsController(OddsService oddsService, GameService gameService) {
        this.oddsService = oddsService;
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<OddsModel> addOdds(OddsModel oddsModel) throws Exception {
        //check if input data is ok
        Long gameId = oddsModel.getGameId();
        if(gameId == null)
            throw new ApiRequestException("Game id missing!");
        if(gameService.findById(gameId).isEmpty())
            throw new ApiRequestException("Game to add odds not found!");
        //set mandatory field
        OddsEntity oddsEntity = new OddsEntity();
        OddsEntity existingOddsEntity = oddsService.findOddsByGameId(gameId);
        if(existingOddsEntity != null)
            oddsEntity = existingOddsEntity;
        else
            oddsEntity.setGame(gameService.getGameById(gameId));
        //set not mandatory fields
        Float homeWin = oddsModel.getHomeWin();
        if(homeWin != null)
            oddsEntity.setHomeTeamWin(homeWin);
        Float homeWinDraw = oddsModel.getHomeWinDraw();
        if(homeWinDraw != null)
            oddsEntity.setHomeTeamWinOrDraw(homeWinDraw);
        Float draw = oddsModel.getDraw();
        if(draw != null)
            oddsEntity.setDraw(draw);
        Float awayWinDraw = oddsModel.getAwayWinDraw();
        if(awayWinDraw != null)
            oddsEntity.setAwayTeamWinOrDraw(awayWinDraw);
        Float awayWin = oddsModel.getAwayWin();
        if(awayWin != null)
            oddsEntity.setAwayTeamWin(awayWin);
        //save data
        oddsService.saveOdds(oddsEntity);
        //prepare response
        OddsModel response = new OddsModel();
        response.makeModel(oddsEntity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<OddsModel> getOdds(OddsModel oddsModel) {
        //check if input data is ok
        Long gameId = oddsModel.getGameId();
        if(gameId == null)
            throw new ApiRequestException("Game id missing!");
        if(gameService.findById(gameId).isEmpty())
            throw new ApiRequestException("Game not found!");
        //find odds
        OddsEntity existingOddsEntity = oddsService.findOddsByGameId(gameId);
        if(existingOddsEntity == null)
            throw new ApiRequestException("For given game no odds found!");
        //prepare response
        OddsModel response = new OddsModel();
        response.makeModel(existingOddsEntity);
        return ResponseEntity.ok(response);
    }
}
