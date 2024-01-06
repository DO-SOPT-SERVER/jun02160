package sopt.org.kotlinseminar.controller

import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sopt.org.kotlinseminar.dto.request.customer.CustomerRequest
import sopt.org.kotlinseminar.service.CustomerService

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
class CustomerController(
        private val customerService: CustomerService
) {

    @PostMapping
    fun createCustomer(@RequestBody request: CustomerRequest): ResponseEntity<Void> {
        customerService.create(request)
        return ResponseEntity.ok().build()
    }
}