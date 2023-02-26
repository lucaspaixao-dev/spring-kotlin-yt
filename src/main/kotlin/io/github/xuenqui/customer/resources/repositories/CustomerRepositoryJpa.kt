package io.github.xuenqui.customer.resources.repositories

import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.CustomerRepository
import java.time.LocalDateTime
import java.util.Optional
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class CustomerSql(
    private val repository: CustomerRepositoryJpa
) : CustomerRepository {

    override fun save(customer: Customer): Customer {
        val entity = CustomerEntity(
            id = UUID.randomUUID().toString(),
            name = customer.name,
            documentNumber = customer.documentNumber,
            email = customer.email,
            cellphone = customer.cellphone,
            createdAt = LocalDateTime.now()
        )

        repository.save(entity)

        return customer.copy(
            id = entity.id,
            createdAt = entity.createdAt
        )
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

fun CustomerEntity.toDomain(): Customer =
    Customer(
        id = this.id,
        name = this.name!!,
        documentNumber = this.documentNumber!!,
        cellphone = this.cellphone!!,
        email = this.email!!,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )

@Repository
interface CustomerRepositoryJpa : CrudRepository<CustomerEntity, String> {

    fun findByDocumentNumber(documentNumber: String): Optional<CustomerEntity>
}
