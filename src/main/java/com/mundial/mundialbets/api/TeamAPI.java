package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.MatchEntity;
import com.mundial.mundialbets.Entities.TeamEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/team")
public interface TeamAPI {

    @PutMapping("/{id}")
    ResponseEntity<TeamEntity> updateTeam(@PathVariable Long id, @RequestBody TeamEntity teamEntity);

    @PostMapping
    ResponseEntity<TeamEntity> addTeam(@RequestBody TeamEntity teamEntity);

    @GetMapping
    ResponseEntity<List<TeamEntity>> getTeam();

    @GetMapping("/{id}")
    ResponseEntity<TeamEntity> getTeamById(@PathVariable Long id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTeamById(@PathVariable Long id);
}
