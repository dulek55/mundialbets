package com.mundial.mundialbets.Services;

import com.mundial.mundialbets.Entities.OddsEntity;
import com.mundial.mundialbets.Repositories.OddsRepository;
import org.springframework.stereotype.Service;

@Service
public class OddsService {
    private final OddsRepository oddsRepository;

    public OddsService(OddsRepository oddsRepository) {
        this.oddsRepository = oddsRepository;
    }

    public OddsEntity saveOdds(OddsEntity oddsEntity) {
        return oddsRepository.save(oddsEntity);
    }

    public OddsEntity findOddsByGameId(Long gameId) {
        return oddsRepository.findOddsByGameId(gameId);
    }
}
