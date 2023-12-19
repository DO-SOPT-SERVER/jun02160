package sopt.org.kotlinseminar.repository

import org.springframework.data.jpa.repository.JpaRepository
import sopt.org.kotlinseminar.domain.CustomerEntity

interface CustomerJpaRepository: JpaRepository<CustomerEntity, Long> {
}