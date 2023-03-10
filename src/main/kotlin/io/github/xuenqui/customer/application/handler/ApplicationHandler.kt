package io.github.xuenqui.customer.application.handler

import io.github.xuenqui.customer.application.exceptions.ApiException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ApplicationHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(
        apiException: ApiException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val statusCode = apiException.status

        val errorResponse = ErrorResponse(
            errorCode = statusCode.name,
            message = apiException.message ?: "",
            details = apiException.details
        )

        return ResponseEntity(errorResponse, statusCode)
    }

    @ExceptionHandler(Exception::class)
    fun handleApiException(
        exception: Exception,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val statusCode = HttpStatus.INTERNAL_SERVER_ERROR

        val errorResponse = ErrorResponse(
            errorCode = statusCode.name,
            message = exception.message ?: ""
        )

        return ResponseEntity(errorResponse, statusCode)
    }
}

data class ErrorResponse(
    val errorCode: String,
    val message: String,
    val details: Map<String, String>? = null
)
