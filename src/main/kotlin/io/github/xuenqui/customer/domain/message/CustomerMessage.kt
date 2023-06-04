package io.github.xuenqui.customer.domain.message

import io.github.xuenqui.customer.domain.Customer

interface CustomerMessage {

    fun produce(customer: Customer)
}
