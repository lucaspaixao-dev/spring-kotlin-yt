package io.github.xuenqui.customer.resources.repositories

import io.github.xuenqui.customer.domain.Customer
import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Component
class CustomerSql(
    private val repository: CustomerRepository
) {

    fun save(customer: Customer): Customer {
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
            id = customer.id,
            createdAt = customer.createdAt
        )
    }
}

@Repository
interface CustomerRepository : CrudRepository<CustomerEntity, String>
