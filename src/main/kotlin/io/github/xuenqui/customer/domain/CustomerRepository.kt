package io.github.xuenqui.customer.domain

interface CustomerRepository {
    fun save(customer: Customer): Customer

    fun findById(id: String): Customer?

    fun findDocumentNumber(documentNumber: String): Customer?
}