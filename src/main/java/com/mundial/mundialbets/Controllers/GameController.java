package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Services.GameService;
import com.mundial.mundialbets.api.GameAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController implements GameAPI {
    private final GameService gameService;
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<GameEntity> updateGame(Long id, GameEntity gameEntity) throws Exception {
        GameEntity updateEntity = gameService.updateGame(id, gameEntity);
        return ResponseEntity.ok(updateEntity);
    }

    @Override
    public ResponseEntity<GameEntity> addGame(GameEntity gameEntity) {
        GameEntity addGame = gameService.saveGame(gameEntity);
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
