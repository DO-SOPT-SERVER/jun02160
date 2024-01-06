package com.server.dosopt.seminar.controller;

import com.server.dosopt.seminar.dto.request.customer.CustomerRequest;
import com.server.dosopt.seminar.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody CustomerRequest request) {
        customerService.create(request);
        return ResponseEntity.ok().build();
    }
}
