package com.server.dosopt.seminar.repository;

import com.server.dosopt.seminar.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
