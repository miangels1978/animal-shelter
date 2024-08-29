package com.AnimalShelter.config;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {





        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()  // Desactiva la protección CSRF
                    .authorizeRequests()
                    .anyRequest().permitAll();  // Permite todas las solicitudes sin autenticación

            return http.build();
        }
    }

