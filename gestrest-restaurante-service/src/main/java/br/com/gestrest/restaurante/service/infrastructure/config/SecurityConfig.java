package br.com.gestrest.restaurante.service.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.gestrest.restaurante.service.adapters.in.web.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.POST, "/api/v1/restaurantes/**").hasRole("DONO")
                .requestMatchers(HttpMethod.PUT, "/api/v1/restaurantes/**").hasRole("DONO")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/restaurantes/**").hasRole("DONO")
                .requestMatchers(HttpMethod.POST, "/api/v1/itens-cardapio/**").hasRole("DONO")
                .requestMatchers(HttpMethod.PUT, "/api/v1/itens-cardapio/**").hasRole("DONO")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/itens-cardapio/**").hasRole("DONO")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
