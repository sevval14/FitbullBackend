package com.example.fitbull.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.fitbull.security.JwtAuthenticationEntryPoint;
import com.example.fitbull.security.JwtAuthenticationFilter;
import com.example.fitbull.service.UserDetailsServiceImpl;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private UserDetailsServiceImpl userDetailsService;
	
	private JwtAuthenticationEntryPoint handler;
	
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    //I gave all permission from coming frontEnd side
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
    		.cors()
    		.and()
    		.csrf().disable()
    		.exceptionHandling().authenticationEntryPoint(handler).and()
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    		.authorizeRequests()
    		.requestMatchers(HttpMethod.GET, "/dashboard")
    		.permitAll()
    		.requestMatchers(HttpMethod.GET, "/gym/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.POST, "/gym/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.PUT, "/gym/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.GET, "/activities/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.POST, "/activities/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.GET, "/educators/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.POST, "/educators/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.GET, "/upload_image/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.POST, "/upload_image/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.GET, "/equipments/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.POST, "/equipments/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.GET, "/services/**")
    		.permitAll()
    		.requestMatchers(HttpMethod.POST, "/services/**")
    		.permitAll()
    		.requestMatchers("/auth/**")
    		.permitAll()
    		.anyRequest().authenticated();
    		
    	httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	return httpSecurity.build();
    }
}
