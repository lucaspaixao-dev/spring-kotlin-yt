package io.github.xuenqui.customer.domain

interface CustomerRepository {
    fun save(customer: Customer): Customer
}