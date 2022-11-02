package com.mundial.mundialbets.ImplementationClasses;

import com.mundial.mundialbets.Entities.TeamEntity;
import com.mundial.mundialbets.Repositories.TeamRepository;
import com.mundial.mundialbets.Repositories.TeamRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class TeamRepositoryImpl implements TeamRepositoryCustom {
    @Autowired
    @Lazy
    TeamRepository teamRepository;
    List<TeamEntity> allTeams;

    public TeamEntity getTeamByCountryCode(String code) {
        allTeams = teamRepository.findAll();
        for (TeamEntity team : allTeams) {
            if(team.getCountryCode().equals(code)) return team;
        }
        return null;
    }
}
