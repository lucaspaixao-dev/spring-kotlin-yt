package io.github.xuenqui.customer.domain.producers

import io.github.xuenqui.customer.domain.Customer

interface CustomerProducer {

    fun produce(customer: Customer)
}
