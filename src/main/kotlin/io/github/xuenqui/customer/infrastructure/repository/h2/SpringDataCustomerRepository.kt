package io.github.xuenqui.customer.infrastructure.repository.h2

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SpringDataCustomerRepository : CrudRepository<CustomerEntity, String> {

    fun findByDocumentNumber(documentNumber: String): Optional<CustomerEntity>
}
