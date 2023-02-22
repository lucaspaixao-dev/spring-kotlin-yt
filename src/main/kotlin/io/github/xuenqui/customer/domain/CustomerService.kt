package io.github.xuenqui.customer.domain

import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository: CustomerRepository
) {

    fun createCustomer(customer: Customer): Customer = repository.save(customer)
}