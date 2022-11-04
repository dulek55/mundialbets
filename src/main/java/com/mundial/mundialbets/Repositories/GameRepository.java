package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
}
