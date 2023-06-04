package io.github.xuenqui.customer.domain.service

import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.ResourceNotFoundException
import io.github.xuenqui.customer.domain.message.CustomerMessage
import io.github.xuenqui.customer.domain.repository.CustomerRepository


class DomainCustomerService(
    private val repository: CustomerRepository,
    private val producer: CustomerMessage
) : CustomerService {

    override fun createCustomer(customer: Customer): Customer {
        val createdCustomer = repository.save(customer)
        producer.produce(createdCustomer)

        return createdCustomer
    }

    override fun findById(id: String): Customer =
        repository.findById(id) ?: throw ResourceNotFoundException("Customer $id not found.")

    override fun findByDocumentNumber(documentNumber: String): Customer =
        repository.findDocumentNumber(documentNumber)
            ?: throw ResourceNotFoundException("Customer $documentNumber not found.")
}