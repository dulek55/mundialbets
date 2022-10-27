package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Models.GameModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/games")
public interface GameAPI {

    @PutMapping("/{id}")
    ResponseEntity<GameEntity> updateGame(@PathVariable Long id, @RequestBody GameEntity gameEntity) throws Exception;

//    @PostMapping
//    ResponseEntity<GameEntity> addGame(@RequestBody GameEntity gameEntity);

    @PostMapping
    ResponseEntity<GameEntity> addGame(@RequestBody GameModel gameModel);

    @GetMapping
    ResponseEntity<List<GameEntity>> getGame();

    @GetMapping("/{id}")
    ResponseEntity<GameEntity> getGameById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteGameById(@PathVariable Long id) throws Exception;
}
