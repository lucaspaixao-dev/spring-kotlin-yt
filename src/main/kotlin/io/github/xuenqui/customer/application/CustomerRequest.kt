package io.github.xuenqui.customer.application

data class CustomerRequest(
    val name: String,
    val email: String,
    val documentNumber: String,
    val cellphone: String,
)
