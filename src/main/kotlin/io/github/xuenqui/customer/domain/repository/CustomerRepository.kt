package io.github.xuenqui.customer.domain.repository

import io.github.xuenqui.customer.domain.Customer

interface CustomerRepository {
    fun save(customer: Customer): Customer

    fun findById(id: String): Customer?

    fun findDocumentNumber(documentNumber: String): Customer?
}