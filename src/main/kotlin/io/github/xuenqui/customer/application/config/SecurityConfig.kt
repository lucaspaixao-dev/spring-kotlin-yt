package io.github.xuenqui.customer.application.config

import io.github.xuenqui.customer.application.security.JwtAuthConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    private val jwtAuthConverter: JwtAuthConverter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        configureAuthorization(http)
        configureResourceServer(http)
        configureSessionManagement(http)
        return http.build()
    }

    private fun configureAuthorization(http: HttpSecurity) {
        http.authorizeHttpRequests()
            .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
            .anyRequest().authenticated()
    }

    private fun configureResourceServer(http: HttpSecurity) {
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter)
    }

    private fun configureSessionManagement(http: HttpSecurity) {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }
}
