package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.TeamEntity;
import com.mundial.mundialbets.Repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamEntity saveTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity);
    }

    public TeamEntity updateTeam(Long id, TeamEntity teamEntity) throws Exception {
        teamRepository.findById(id).orElseThrow(() -> new Exception("Team not found", new Error("TEAM NOT FOUND")));
        teamEntity.setId(id);
        return teamRepository.save(teamEntity);
    }

    public List<TeamEntity> getTeam() {
        return teamRepository.findAll();
    }

    public TeamEntity getTeamById(Long id) throws Exception {
        return teamRepository.findById(id)
                .orElseThrow(() -> new Exception("Team id not found : " + id, new Error("TEAM NOT FOUND")));
    }

    public void deleteTeam(Long id) throws Exception {
        teamRepository.findById(id).orElseThrow(() -> new Exception("Team="+ id +" does not exist", new Error("TEAM NOT FOUND")));
        teamRepository.deleteById(id);
    }
}
