package io.github.xuenqui.customer.domain

open class ApplicationException(
    message: String,
    val status: Int,
    throwable: Throwable? = null,
    val details: Map<String, String>? = null
) : RuntimeException(message, throwable)

class ResourceNotFoundException(message: String, throwable: Throwable? = null) :
    ApplicationException(message, 404, throwable)

class DatabaseException(message: String, throwable: Throwable) :
    ApplicationException(message, 500, throwable)