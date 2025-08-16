package com.example.springcloud.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
public class ResourceServerConfig {

    @Bean
    JwtAuthenticationConverter jwtAuthConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(server -> server.jwt().jwtAuthenticationConverter(jwtAuthConverter()));
    
        http.authorizeHttpRequests(auth -> auth
        .requestMatchers(new RegexRequestMatcher("/couponapi/coupons/[A-Z0-9]+", "GET"))
            .hasAnyRole("USER", "ADMIN")
        .requestMatchers(new RegexRequestMatcher("/couponapi/coupons", HttpMethod.POST.name()))
            .hasRole("ADMIN")
        .anyRequest().authenticated());

        http.csrf(csrf -> csrf.disable());
            
        return http.build();
    }
}
