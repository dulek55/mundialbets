package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.GameEntity;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.GameModel;
import com.mundial.mundialbets.Services.GameService;
import com.mundial.mundialbets.Services.TeamService;
import com.mundial.mundialbets.api.GameAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public ResponseEntity<GameModel> updateGame(Long id, GameModel gameModel) throws Exception {
        GameEntity updateGame = gameService.getGameById(id);
        if(gameModel.getHomeTeamCode() != null) {
            if(teamService.getTeamByCountryCode(gameModel.getHomeTeamCode()) == null)
                throw new ApiRequestException("Home team not found!");
            updateGame.setHomeTeam(teamService.getTeamByCountryCode(gameModel.getHomeTeamCode()));
        }
        if(gameModel.getAwayTeamCode() != null) {
            if(teamService.getTeamByCountryCode(gameModel.getAwayTeamCode()) == null)
                throw new ApiRequestException("Away team not found!");
            updateGame.setAwayTeam(teamService.getTeamByCountryCode(gameModel.getAwayTeamCode()));
        }
        if(gameModel.getDateTime() != null)
            updateGame.setGameDate(gameModel.getDateTime());
        if(gameModel.getHomeScore() != null)
            updateGame.setHomeScore(gameModel.getHomeScore());
        if(gameModel.getAwayScore() != null)
            updateGame.setAwayScore(gameModel.getAwayScore());
        if(gameModel.getHomeScoreAfterOvertime() != null)
            updateGame.setHomeScoreAfterOvertime(gameModel.getHomeScoreAfterOvertime());
        if(gameModel.getAwayScoreAfterOvertime() != null)
            updateGame.setAwayScoreAfterOvertime(gameModel.getAwayScoreAfterOvertime());
        gameService.updateGame(id, updateGame);
        GameModel modelToReturn = new GameModel();
        modelToReturn.makeModel(updateGame);
        return ResponseEntity.ok(modelToReturn);
    }

    @Override
    public ResponseEntity<GameModel> addGame(GameModel gameModel) {
        GameEntity addGame = new GameEntity();
        if(teamService.getTeamByCountryCode(gameModel.getHomeTeamCode()) == null)
            throw new ApiRequestException("Home team not found!");
        if(teamService.getTeamByCountryCode(gameModel.getAwayTeamCode()) == null)
            throw new ApiRequestException("Away team not found!");
        addGame.setHomeTeam(teamService.getTeamByCountryCode(gameModel.getHomeTeamCode()));
        addGame.setAwayTeam(teamService.getTeamByCountryCode(gameModel.getAwayTeamCode()));
        addGame.setGameDate(gameModel.getDateTime());
        gameService.saveGame(addGame);
        GameModel modelToReturn = new GameModel();
        modelToReturn.makeModel(addGame);
        return ResponseEntity.ok(modelToReturn);
    }

    @Override
    public ResponseEntity<List<GameModel>> getGame() {
        List<GameModel> gameModels = new ArrayList<>();
        for (GameEntity game : gameService.getGames()) {
            GameModel gameModel = new GameModel();
            gameModel.makeModel(game);
            gameModels.add(gameModel);
        }
        return ResponseEntity.ok(gameModels);
    }

    @Override
    public ResponseEntity<GameEntity> getGameById(Long id) throws Exception {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @Override
    public ResponseEntity<String> deleteGameById(Long id) throws Exception {
        if(gameService.findById(id).isEmpty())
            throw new ApiRequestException("Game to delete not found!");
        String response = "Deleted game. id: " + id.toString();
        gameService.deleteGame(id);
        return ResponseEntity.ok(response);
    }
}
