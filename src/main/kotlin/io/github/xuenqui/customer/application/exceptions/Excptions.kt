package io.github.xuenqui.customer.application.exceptions

import org.springframework.http.HttpStatus

open class ApiException(
    message: String,
    val status: HttpStatus,
    throwable: Throwable? = null,
    val details: Map<String, String>? = null
) : RuntimeException(message, throwable)

class ResourceNotFoundException(message: String, throwable: Throwable? = null):
        ApiException(message, HttpStatus.NOT_FOUND, throwable)

class DatabaseException(message: String, throwable: Throwable):
        ApiException(message, HttpStatus.INTERNAL_SERVER_ERROR, throwable)
