package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.GameModel;
import com.mundial.mundialbets.Services.GameService;
import com.mundial.mundialbets.Services.TeamService;
import com.mundial.mundialbets.api.GameAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController implements GameAPI {
    private final GameService gameService;
    private final TeamService teamService;

    public GameController(GameService gameService, TeamService teamService){
        this.gameService = gameService;
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<GameEntity> updateGame(Long id, GameEntity gameEntity) throws Exception {
        GameEntity updateEntity = gameService.updateGame(id, gameEntity);
        return ResponseEntity.ok(updateEntity);
    }

//    @Override
//    public ResponseEntity<GameEntity> addGame(GameEntity gameEntity) {
//        GameEntity addGame = gameService.saveGame(gameEntity);
//        return ResponseEntity.ok(addGame);
//    }

    @Override
    public ResponseEntity<GameEntity> addGame(GameModel gameModel) {
        GameEntity addGame = new GameEntity();
        if(teamService.getTeamByCountryCode(gameModel.getHomeTeamCode()) == null)
            throw new ApiRequestException("Home team not found!");
        if(teamService.getTeamByCountryCode(gameModel.getAwayTeamCode()) == null)
            throw new ApiRequestException("Away team not found!");
        addGame.setHomeTeam(teamService.getTeamByCountryCode(gameModel.getHomeTeamCode()));
        addGame.setAwayTeam(teamService.getTeamByCountryCode(gameModel.getAwayTeamCode()));
        addGame.setGameDate(gameModel.getDateTime());
        gameService.saveGame(addGame);
        return ResponseEntity.ok(addGame);
    }

    @Override
    public ResponseEntity<List<GameEntity>> getGame() {
        return ResponseEntity.ok(gameService.getGames());
    }

    @Override
    public ResponseEntity<GameEntity> getGameById(Long id) throws Exception {
        return ResponseEntity.ok(gameService.getGamesById(id));
    }

    @Override
    public ResponseEntity<?> deleteGameById(Long id) throws Exception {
        gameService.deleteGame(id);
        return null;
    }
}
