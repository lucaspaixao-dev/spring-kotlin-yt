package io.github.xuenqui.customer.domain.service

import io.github.xuenqui.customer.domain.Customer

interface CustomerService {

    fun createCustomer(customer: Customer): Customer
    fun findById(id: String): Customer

    fun findByDocumentNumber(documentNumber: String): Customer
}