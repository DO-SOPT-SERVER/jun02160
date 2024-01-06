package sopt.org.kotlinseminar.service

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sopt.org.kotlinseminar.domain.CustomerEntity
import sopt.org.kotlinseminar.dto.request.customer.CustomerRequest
import sopt.org.kotlinseminar.repository.CustomerJpaRepository

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class CustomerService(
        private val customerJpaRepository: CustomerJpaRepository
) {

    @Transactional
    fun create(request: CustomerRequest) {
        val customer = CustomerEntity(request.name, request.age, request.nickname)
        println(customer)
        customerJpaRepository.save(customer)
    }
}