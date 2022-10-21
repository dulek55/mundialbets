package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.MatchEntity;
import com.mundial.mundialbets.Services.MatchService;
import com.mundial.mundialbets.api.MatchAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MatchController implements MatchAPI {
    private final MatchService matchService;
    public MatchController (MatchService matchService){
        this.matchService = matchService;
    }

    @Override
    public ResponseEntity<MatchEntity> updateMatch(Long id, MatchEntity matchEntity) throws Exception {
        MatchEntity updateEntity = matchService.updateMatch(id, matchEntity);
        return ResponseEntity.ok(updateEntity);
    }

    @Override
    public ResponseEntity<MatchEntity> addMatch(MatchEntity matchEntity) {
        MatchEntity addMatch = matchService.saveMatch(matchEntity);
        return ResponseEntity.ok(addMatch);
    }

    @Override
    public ResponseEntity<List<MatchEntity>> getMatch() {
        return ResponseEntity.ok(matchService.getMatches());
    }

    @Override
    public ResponseEntity<MatchEntity> getMatchById(Long id) throws Exception {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }

    @Override
    public ResponseEntity<?> deleteMatchById(Long id) throws Exception {
        matchService.deleteMatch(id);
        return null;
    }
}
