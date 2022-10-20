package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.MatchEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/matches")
public interface MatchAPI {

    @PutMapping("/{id}")
    ResponseEntity<MatchEntity> updateMatch(@PathVariable Long id, @RequestBody MatchEntity matchEntity);

    @PostMapping
    ResponseEntity<MatchEntity> addMatch(@RequestBody MatchEntity matchEntity);

    @GetMapping
    ResponseEntity<List<MatchEntity>> getMatch();

    @GetMapping("/{id}")
    ResponseEntity<MatchEntity> getMatchById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMatchById(@PathVariable Long id);
}
