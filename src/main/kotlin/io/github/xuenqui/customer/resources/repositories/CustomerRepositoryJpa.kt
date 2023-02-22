package io.github.xuenqui.customer.resources.repositories

import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.CustomerRepository
import java.time.LocalDateTime
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
}

@Repository
interface CustomerRepositoryJpa : CrudRepository<CustomerEntity, String>
