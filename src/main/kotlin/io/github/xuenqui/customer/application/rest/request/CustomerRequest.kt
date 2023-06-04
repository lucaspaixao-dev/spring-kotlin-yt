package io.github.xuenqui.customer.application.rest.request

import io.github.xuenqui.customer.domain.Customer

data class CustomerRequest(
    val name: String,
    val email: String,
    val documentNumber: String,
    val cellphone: String,
)

fun CustomerRequest.toDomain() = Customer(
    name = name,
    email = email,
    cellphone = cellphone,
    documentNumber = documentNumber
)
