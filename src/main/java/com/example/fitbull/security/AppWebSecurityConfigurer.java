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
        http
            .csrf().disable() // CSRF korumasını devre dışı bırak
            .authorizeRequests()
                .requestMatchers("/users/**").permitAll()
                .anyRequest().authenticated() // Diğer tüm isteklerin kimlik doğrulamasını gerektir
            .and()
            .httpBasic(); // HTTP Basic kimlik doğrulama kullan
        return http.build();
    }
}
