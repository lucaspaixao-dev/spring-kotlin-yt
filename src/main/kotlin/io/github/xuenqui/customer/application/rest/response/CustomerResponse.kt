package io.github.xuenqui.customer.application.rest.response

import io.github.xuenqui.customer.domain.Customer
import java.time.LocalDateTime

data class CustomerResponse(
    val id: String,
    val name: String,
    val email: String,
    val documentNumber: String,
    val cellphone: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null
)

fun Customer.toResponse() = CustomerResponse(
    id = id!!,
    name = name,
    email = email,
    cellphone = cellphone,
    documentNumber = documentNumber,
    createdAt = createdAt!!,
    updatedAt = updatedAt
)
