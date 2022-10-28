package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameEntity saveGame(GameEntity gameEntity) {
        return gameRepository.save(gameEntity);
    }

    public GameEntity updateGame(Long id, GameEntity gameEntity) throws Exception {
        gameRepository.findById(id).orElseThrow(() -> new Exception("Game not found", new Error("Game NOT FOUND")));
        gameEntity.setId(id);
        return gameRepository.save(gameEntity);
    }

    public List<GameEntity> getGames() {
        return gameRepository.findAll();
    }

    public GameEntity getGamesById(Long id) throws Exception {
        return gameRepository.findById(id)
                .orElseThrow(() -> new Exception("Game id not found : " + id, new Error("Game NOT FOUND")));
    }

    public Optional<GameEntity> findById(Long id) {
        return gameRepository.findById(id);
    }

    public void deleteGame(Long id) throws Exception {
        gameRepository.findById(id).orElseThrow(() -> new Exception("Game="+ id +" does not exist", new Error("GAME NOT FOUND")));
        gameRepository.deleteById(id);
    }
}
