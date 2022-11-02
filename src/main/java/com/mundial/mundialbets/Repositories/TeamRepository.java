package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Long>, TeamRepositoryCustom {
}
