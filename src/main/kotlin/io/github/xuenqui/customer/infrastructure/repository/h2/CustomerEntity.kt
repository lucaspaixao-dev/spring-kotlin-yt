package io.github.xuenqui.customer.infrastructure.repository.h2

import io.github.xuenqui.customer.domain.Customer
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "customers")
class CustomerEntity(

    @Id
    var id: String? = null,

    var name: String? = null,

    @Column(unique = true)
    var email: String? = null,

    @Column(unique = true)
    var documentNumber: String? = null,

    var cellphone: String? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)

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