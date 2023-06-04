package io.github.xuenqui.customer.infrastructure.repository.h2

import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.DatabaseException
import io.github.xuenqui.customer.domain.repository.CustomerRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class H2CustomerRepository(
    private val repository: SpringDataCustomerRepository
) : CustomerRepository {

    override fun save(customer: Customer): Customer =
        try {
            val entity = CustomerEntity(
                id = UUID.randomUUID().toString(),
                name = customer.name,
                documentNumber = customer.documentNumber,
                email = customer.email,
                cellphone = customer.cellphone,
                createdAt = LocalDateTime.now()
            )

            repository.save(entity)

            customer.copy(
                id = entity.id,
                createdAt = entity.createdAt
            )
        } catch (e: Exception) {
            throw DatabaseException("Error to create a new customer.", e)
        }


    override fun findById(id: String): Customer? {
        val result = repository.findById(id)

        return result.map { it.toDomain() }
            .orElse(null)
    }

    override fun findDocumentNumber(documentNumber: String): Customer? {
        val result = repository.findByDocumentNumber(documentNumber)

        return result.map { it.toDomain() }
            .orElse(null)
    }
}

