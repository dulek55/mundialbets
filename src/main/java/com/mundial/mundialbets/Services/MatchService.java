package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.MatchEntity;
import com.mundial.mundialbets.Entities.UserEntity;
import com.mundial.mundialbets.Repositories.MatchRepository;
import com.mundial.mundialbets.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchEntity saveMatch(MatchEntity matchEntity) {
        return matchRepository.save(matchEntity);
    }

    public MatchEntity updateMatch(Long id, MatchEntity matchEntity) throws Exception {
        matchRepository.findById(id).orElseThrow(() -> new Exception("Match not found", new Error("MATCH NOT FOUND")));
        matchEntity.setId(id);
        return matchRepository.save(matchEntity);
    }

    public List<MatchEntity> getMatches() {
        return matchRepository.findAll();
    }

    public MatchEntity getMatchById(Long id) throws Exception {
        return matchRepository.findById(id)
                .orElseThrow(() -> new Exception("Match id not found : " + id, new Error("MATCH NOT FOUND")));
    }

    public void deleteMatch(Long id) throws Exception {
        matchRepository.findById(id).orElseThrow(() -> new Exception("Match="+ id +" does not exist", new Error("MATCH NOT FOUND")));
        matchRepository.deleteById(id);
    }
}
