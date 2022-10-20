package com.mundial.mundialbets.Repositories;

import com.mundial.mundialbets.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
