package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Models.GameModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/games")
public interface GameAPI {

    @PutMapping("/{id}")
    ResponseEntity<GameModel> updateGame(@PathVariable Long id, @RequestBody GameModel gameModel) throws Exception;

    @PostMapping
    ResponseEntity<GameModel> addGame(@RequestBody GameModel gameModel);

    @GetMapping
    ResponseEntity<List<GameModel>> getGame();

    @GetMapping("/{id}")
    ResponseEntity<GameEntity> getGameById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteGameById(@PathVariable Long id) throws Exception;
}
