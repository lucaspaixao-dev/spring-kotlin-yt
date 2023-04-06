package io.github.xuenqui.customer.resources.sqs

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.xuenqui.customer.application.config.AWSProperties
import io.github.xuenqui.customer.domain.Customer
import io.github.xuenqui.customer.domain.producers.CustomerProducer
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue
import software.amazon.awssdk.services.sqs.model.SendMessageRequest
import java.util.*

@Component
class CustomerSQSProducer(
    private val sqsClient: SqsClient,
    private val objectMapper: ObjectMapper,
    private val awsProperties: AWSProperties
): CustomerProducer {

    override fun produce(customer: Customer) {
        val messageId = MessageAttributeValue.builder()
            .dataType("String")
            .stringValue(UUID.randomUUID().toString())
            .build()

        val attributesValues = mapOf(
            "messageId" to messageId
        )

        val messageRequest = SendMessageRequest.builder()
            .queueUrl(awsProperties.customerQueue)
            .messageBody(objectMapper.writeValueAsString(customer))
            .messageAttributes(attributesValues)
            .build()

        try {
            sqsClient.sendMessage(messageRequest)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}