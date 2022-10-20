package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
}
