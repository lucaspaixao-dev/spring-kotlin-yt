package io.github.xuenqui.customer.application.rest.response

data class ErrorResponse(
    val errorCode: String,
    val message: String,
    val details: Map<String, String>? = null
)
