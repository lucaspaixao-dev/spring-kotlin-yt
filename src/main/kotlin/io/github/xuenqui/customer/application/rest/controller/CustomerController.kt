package io.github.xuenqui.customer.application.rest.controller

import io.github.xuenqui.customer.application.rest.request.CustomerRequest
import io.github.xuenqui.customer.application.rest.request.toDomain
import io.github.xuenqui.customer.application.rest.response.CustomerResponse
import io.github.xuenqui.customer.application.rest.response.toResponse
import io.github.xuenqui.customer.domain.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val service: CustomerService
) {

    @PostMapping
    fun post(@RequestBody request: CustomerRequest): ResponseEntity<CustomerResponse> {
        val response = service.createCustomer(request.toDomain()).toResponse()
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: String): ResponseEntity<CustomerResponse> {
        val response = service.findById(id).toResponse()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping
    fun findByDocumentNumber(@RequestParam("document_number") documentNumber: String): ResponseEntity<CustomerResponse> {
        val response = service.findByDocumentNumber(documentNumber).toResponse()
        return ResponseEntity(response, HttpStatus.OK)
    }
}
