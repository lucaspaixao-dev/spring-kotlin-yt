package io.github.xuenqui.customer.domain

import io.github.xuenqui.customer.application.exceptions.ResourceNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository: CustomerRepository
) {

    fun createCustomer(customer: Customer): Customer = repository.save(customer)

    fun findById(id: String): Customer =
        repository.findById(id) ?: throw ResourceNotFoundException("Customer $id not found.")

    fun findByDocumentNumber(documentNumber: String): Customer =
        repository.findDocumentNumber(documentNumber)
            ?: throw ResourceNotFoundException("Customer $documentNumber not found.")
}