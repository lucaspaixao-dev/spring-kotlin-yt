package io.github.xuenqui.customer.application.rest.handler

import io.github.xuenqui.customer.application.rest.response.ErrorResponse
import io.github.xuenqui.customer.domain.ApplicationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class DefaultExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ApplicationException::class)
    fun handleApiException(applicationException: ApplicationException): ResponseEntity<ErrorResponse> {
        return createErrorResponse(
            HttpStatus.valueOf(applicationException.status),
            applicationException.message,
            applicationException.details
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorResponse> {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
    }

    private fun createErrorResponse(
        status: HttpStatus,
        message: String?,
        details: Map<String, String>? = null
    ): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            errorCode = status.name,
            message = message ?: "",
            details = details
        )

        return ResponseEntity(errorResponse, status)
    }
}
