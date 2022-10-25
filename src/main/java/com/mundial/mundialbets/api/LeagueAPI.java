package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.LeagueEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/leagues")
public interface LeagueAPI {
    @PutMapping("/{id}")
    ResponseEntity<LeagueEntity> updateLeague(@PathVariable Long id, @RequestBody LeagueEntity leagueEntity) throws Exception;

    @PostMapping
    ResponseEntity<LeagueEntity> addLeague(@RequestBody LeagueEntity leagueEntity);

    @GetMapping
    ResponseEntity<List<LeagueEntity>> getLeague();

    @GetMapping("/{id}")
    ResponseEntity<LeagueEntity> getLeagueById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteLeagueById(@PathVariable Long id) throws Exception;
}
