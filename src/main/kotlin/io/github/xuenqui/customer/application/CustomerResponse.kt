package io.github.xuenqui.customer.application

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
