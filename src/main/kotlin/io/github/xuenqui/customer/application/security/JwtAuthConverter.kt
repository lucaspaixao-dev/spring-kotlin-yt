package io.github.xuenqui.customer.application.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.JwtClaimNames
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component

@Component
class JwtAuthConverter(private val properties: JwtAuthConverterProperties) :
    Converter<Jwt, AbstractAuthenticationToken> {

    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()

    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val authorities = mergeAuthorities(jwt)
        return JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt))
    }

    private fun mergeAuthorities(jwt: Jwt): Collection<GrantedAuthority> {
        val defaultAuthorities = jwtGrantedAuthoritiesConverter.convert(jwt).orEmpty().asSequence()
        val resourceRoles = extractResourceRoles(jwt)
        return (defaultAuthorities + resourceRoles).toSet()
    }

    private fun getPrincipalClaimName(jwt: Jwt): String {
        val claimName = JwtClaimNames.SUB
        return properties.principalAttribute?.let { jwt.getClaim(it) } ?: jwt.getClaim(claimName)
    }

    private fun extractResourceRoles(jwt: Jwt): Collection<GrantedAuthority> {
        val resourceAccess = jwt.getClaim<Map<String, Any>>("resource_access") ?: return setOf()
        val resource = resourceAccess[properties.resourceId] as? Map<*, *> ?: return setOf()
        val resourceRoles = resource["roles"] as? Collection<*> ?: return setOf()

        return resourceRoles.asSequence()
            .map { role -> SimpleGrantedAuthority("ROLE_$role") }
            .toSet()
    }
}
