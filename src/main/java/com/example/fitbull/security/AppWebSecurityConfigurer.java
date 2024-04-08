package com.example.fitbull.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppWebSecurityConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeRequests().anyRequest().authenticated(); //or whatever else you need
        return http.build();
    }
}
