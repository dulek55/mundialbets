package com.mundial.mundialbets.api;

import com.mundial.mundialbets.Entities.TeamEntity;
import com.mundial.mundialbets.Models.TeamModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/team")
@CrossOrigin
public interface TeamAPI {

    @PutMapping("/{id}")
    ResponseEntity<TeamEntity> updateTeam(@PathVariable Long id, @RequestBody TeamEntity teamEntity) throws Exception;

    @PostMapping
    ResponseEntity<TeamEntity> addTeam(@RequestBody TeamEntity teamEntity);

    @GetMapping
    ResponseEntity<List<TeamModel>> getTeam();

    @GetMapping("/{id}")
    ResponseEntity<TeamEntity> getTeamById(@PathVariable Long id) throws Exception;

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTeamById(@PathVariable Long id) throws Exception;
}
