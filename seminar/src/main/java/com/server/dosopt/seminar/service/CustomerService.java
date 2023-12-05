package com.server.dosopt.seminar.service;

import com.server.dosopt.seminar.domain.CustomerEntity;
import com.server.dosopt.seminar.dto.request.customer.CustomerRequest;
import com.server.dosopt.seminar.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {

    private final CustomerJpaRepository customerJpaRepository;

    @Transactional
    public void create(CustomerRequest request) {
        CustomerEntity customer = CustomerEntity.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .build();
        log.info("customer: {}", customer);
        customerJpaRepository.save(customer);
    }
}
