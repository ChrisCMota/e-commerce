package com.christian.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").permitAll();
                    auth.requestMatchers("/products").permitAll(); // Allows GET requests to /products without authentication
                    auth.requestMatchers( "/customers").permitAll(); // Allows POST requests to /customers without authentication
                    auth.requestMatchers("/customers").permitAll();
                    auth.requestMatchers( "/login").permitAll();
                    auth.requestMatchers("/v3/api-docs/**").permitAll();
                    auth.requestMatchers( "/swagger-ui/**").permitAll();
                    auth.requestMatchers("/swagger-ui.html").permitAll();
                    auth.requestMatchers("/swagger-resources/**").permitAll();
                    auth.requestMatchers("/webjars/**").permitAll();
                    auth.requestMatchers("/favicon.ico").permitAll();
                    auth.requestMatchers("/favicon.svg").permitAll();

                    auth.anyRequest().authenticated(); // Requires authentication for all other requests
                        }

                )
                .cors(Customizer.withDefaults()) // Enables CORS with default settings (can customize it if needed using: cors -> {} )
                .addFilterBefore(new MyEComFilter(), UsernamePasswordAuthenticationFilter.class) // Adds a custom filter before the standard username/password filter
                .build();
    }
}
