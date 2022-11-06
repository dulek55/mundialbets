package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.LeagueEntity;
import com.mundial.mundialbets.Entities.UserBetEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Repositories.LeagueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public LeagueEntity saveLeague(LeagueEntity leagueEntity) {
        return leagueRepository.save(leagueEntity);
    }

    public LeagueEntity updateLeague(Long id, LeagueEntity leagueEntity) throws Exception {
        leagueRepository.findById(id).orElseThrow(() -> new Exception("League not found", new Error("League NOT FOUND")));
        leagueEntity.setId(id);
        return leagueRepository.save(leagueEntity);
    }

    public List<LeagueEntity> getLeagues() {
        return leagueRepository.findAll();
    }

    public LeagueEntity getLeagueById(Long id) throws Exception {
        return leagueRepository.findById(id)
                .orElseThrow(() -> new Exception("League id not found : " + id, new Error("League NOT FOUND")));
    }

    public Optional<LeagueEntity> findById(Long id) {
        return leagueRepository.findById(id);
    }

    public void deleteLeague(Long id) throws Exception {
        leagueRepository.findById(id).orElseThrow(() -> new Exception("League="+ id +" does not exist", new Error("LEAGUE NOT FOUND")));
        leagueRepository.deleteById(id);
    }
}
