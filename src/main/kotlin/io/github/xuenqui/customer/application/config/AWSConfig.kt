package io.github.xuenqui.customer.application.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsClient
import java.net.URI

@Configuration
class AWSConfig(
    private val awsProperties: AWSProperties
) {

    @Bean
    fun createSqsClient(): SqsClient {
        val client = SqsClient
            .builder()
            .region(Region.of(awsProperties.region))

        client.credentialsProvider(
            StaticCredentialsProvider.create(
                AwsBasicCredentials.create(awsProperties.accessKey, awsProperties.secretKey)
            )
        ).endpointOverride(URI.create(awsProperties.endpoint!!))

        return client.build()
    }
}

@Validated
@Configuration
@ConfigurationProperties(prefix = "aws")
data class AWSProperties(
    var accessKey: String? = null,
    var secretKey: String? = null,
    var region: String? = null,
    var endpoint: String? = null,
    var customerQueue: String? = null
)
