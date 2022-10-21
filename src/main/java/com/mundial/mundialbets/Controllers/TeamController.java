package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.TeamEntity;
import com.mundial.mundialbets.Services.TeamService;
import com.mundial.mundialbets.api.TeamAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController implements TeamAPI {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public ResponseEntity<TeamEntity> updateTeam(Long id, TeamEntity teamEntity) throws Exception {
        return ResponseEntity.ok(teamService.updateTeam(id, teamEntity));
    }

    @Override
    public ResponseEntity<TeamEntity> addTeam(TeamEntity teamEntity) {
        return ResponseEntity.ok(teamService.saveTeam(teamEntity));
    }

    @Override
    public ResponseEntity<List<TeamEntity>> getTeam() {
        return ResponseEntity.ok(teamService.getTeam());
    }

    @Override
    public ResponseEntity<TeamEntity> getTeamById(Long id) throws Exception {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @Override
    public ResponseEntity<?> deleteTeamById(Long id) throws Exception {
        teamService.deleteTeam(id);
        return null;
    }
}
