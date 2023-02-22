package io.github.xuenqui.customer.application

import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val service: CustomerService
) {

    @PostMapping
    fun post(
        @RequestBody request: CustomerRequest
    ): ResponseEntity<CustomerResponse> {
        val domain = request.toDomain()
        val response = service.createCustomer(domain).toResponse()

        return ResponseEntity(response, HttpStatus.CREATED)
    }
}

fun CustomerRequest.toDomain() = Customer(
    name = this.name,
    email = this.email,
    cellphone = this.cellphone,
    documentNumber = this.documentNumber
)

fun Customer.toResponse() = CustomerResponse(
    id = this.id!!,
    name = this.name,
    email = this.email,
    cellphone = this.cellphone,
    documentNumber = this.documentNumber,
    createdAt = this.createdAt!!,
    updatedAt = this.updatedAt
)
