package com.jwt.jwt.config.security;


import com.jwt.jwt.exception.CustomAuthenticationEntryPoint;
import com.jwt.jwt.utils.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class securityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomAuthenticationEntryPoint customAuthEntryPoint;

    securityConfig(JwtAuthFilter jwtAuthFilter, CustomAuthenticationEntryPoint customAuthEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.customAuthEntryPoint = customAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth->auth.requestMatchers("/register","/login", "/refresh").permitAll()
        .requestMatchers(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/webjars/**",
            "/v3/api-docs.yaml",
            "/v3/api-docs",
            "/jwt-auth/**"
        ).permitAll()
        .requestMatchers("/allusers").hasAnyRole("ADMIN", "USER") // restrict based on role
        .anyRequest().authenticated())
        .exceptionHandling(exception -> 
        exception.authenticationEntryPoint(customAuthEntryPoint)) // ✅ Set 401 handler
        .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }


}
