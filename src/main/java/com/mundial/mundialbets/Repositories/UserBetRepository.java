package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.UserBetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBetRepository extends JpaRepository<UserBetEntity, Long> {
}
