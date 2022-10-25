package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Services.LeagueService;
import com.mundial.mundialbets.api.LeagueAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeagueController implements LeagueAPI {
    private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @Override
    public ResponseEntity<LeagueEntity> updateLeague(Long id, LeagueEntity leagueEntity) throws Exception {
        return ResponseEntity.ok(leagueService.updateLeague(id, leagueEntity));
    }

    @Override
    public ResponseEntity<LeagueEntity> addLeague(LeagueEntity leagueEntity) {
        return ResponseEntity.ok(leagueService.saveLeague(leagueEntity));
    }

    @Override
    public ResponseEntity<List<LeagueEntity>> getLeague() {
        return ResponseEntity.ok(leagueService.getLeagues());
    }

    @Override
    public ResponseEntity<LeagueEntity> getLeagueById(Long id) throws Exception {
        return ResponseEntity.ok(leagueService.getLeagueById(id));
    }

    @Override
    public ResponseEntity<?> deleteLeagueById(Long id) throws Exception {
        leagueService.deleteLeague(id);
        return null;
    }
}
