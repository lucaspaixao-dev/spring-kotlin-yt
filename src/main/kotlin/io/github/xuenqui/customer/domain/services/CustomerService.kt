package io.github.xuenqui.customer.domain.services

import io.github.xuenqui.customer.application.exceptions.ResourceNotFoundException
import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.producers.CustomerProducer
import io.github.xuenqui.customer.domain.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository: CustomerRepository,
    private val producer: CustomerProducer
) {

    fun createCustomer(customer: Customer): Customer {
        val createdCustomer = repository.save(customer)
        producer.produce(createdCustomer)

        return createdCustomer
    }

    fun findById(id: String): Customer =
        repository.findById(id) ?: throw ResourceNotFoundException("Customer $id not found.")

    fun findByDocumentNumber(documentNumber: String): Customer =
        repository.findDocumentNumber(documentNumber)
            ?: throw ResourceNotFoundException("Customer $documentNumber not found.")
}