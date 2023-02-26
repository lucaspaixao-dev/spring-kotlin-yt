package io.github.xuenqui.customer.domain

import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val repository: CustomerRepository
) {

    fun createCustomer(customer: Customer): Customer = repository.save(customer)

    fun findById(id: String): Customer =
        repository.findById(id) ?: throw Exception("Customer $id not found.")

    fun findByDocumentNumber(documentNumber: String): Customer =
        repository.findDocumentNumber(documentNumber) ?: throw Exception("Customer $documentNumber not found.")
}