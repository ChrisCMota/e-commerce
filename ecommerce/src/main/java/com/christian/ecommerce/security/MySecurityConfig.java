package com.christian.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.csrf(csrf -> csrf.disable()) //Disables CSRF protection
                .authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/products").permitAll() // Allows GET requests to /products without authentication
                        .requestMatchers(HttpMethod.POST, "/customers").permitAll() // Allows POST requests to /customers without authentication
                        .requestMatchers(HttpMethod.GET, "/customers").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .anyRequest().authenticated() // Requires authentication for all other requests
                )
                .cors(Customizer.withDefaults()) // Enables CORS with default settings (can customize it if needed using: cors -> {} )
                .addFilterBefore(new MyEComFilter(), UsernamePasswordAuthenticationFilter.class) // Adds a custom filter before the standard username/password filter
                .build();
    }
}
