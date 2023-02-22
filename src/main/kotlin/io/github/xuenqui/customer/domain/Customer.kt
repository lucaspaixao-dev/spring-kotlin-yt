package io.github.xuenqui.customer.domain

import java.time.LocalDateTime

data class Customer(
    val id: String? = null,
    val name: String,
    val email: String,
    val documentNumber: String,
    val cellphone: String,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)
