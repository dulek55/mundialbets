package com.mundial.mundialbets.Controllers;

import com.mundial.mundialbets.Entities.TeamEntity;
import com.mundial.mundialbets.Exceptions.ApiRequestException;
import com.mundial.mundialbets.Models.TeamModel;
import com.mundial.mundialbets.Services.TeamService;
import com.mundial.mundialbets.api.TeamAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        if(containsDigit(teamEntity.getCountryName()) ||
            containsDigit(teamEntity.getCountryCode()))
            throw new ApiRequestException("Country name and code must not contain digits!");
        return ResponseEntity.ok(teamService.saveTeam(teamEntity));
    }

    @Override
    public ResponseEntity<List<TeamModel>> getTeam() {
        List<TeamModel> teamModels = new ArrayList<>();
        for (TeamEntity team : teamService.getTeam()) {
            TeamModel teamModel = new TeamModel();
            teamModel.makeModel(team);
            teamModels.add(teamModel);
        }
        return ResponseEntity.ok(teamModels);
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

    public final boolean containsDigit(String s) {
        boolean containsDigit = false;

        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }

        return containsDigit;
    }
}
