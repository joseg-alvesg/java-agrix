package com.agrix.auth.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

/**
 * SecurityConfig.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@SecurityScheme(name = "Authentication", type = SecuritySchemeType.HTTP, scheme = "bearer")
public class SecurityConfig {

  private static String[] WHITELIST = {
      "/v3/api-docs/**",
      "/configuration/**",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui/**",
      "/webjars/**"
  };

  private JwtFilter jwtFilter;

  /**
   * Método construtor.
   *
   * @param jwtFilter JwtFilter
   */
  @Autowired
  public SecurityConfig(JwtFilter jwtFilter) {
    this.jwtFilter = jwtFilter;
  }

  /**
   * Método que configura a segurança da aplicação.
   *
   * @param httpSecurity HttpSecurity
   * @return SecurityFilterChain
   * @throws Exception Exceção
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
            .configurationSource(request -> {
              CorsConfiguration corsConfig = new CorsConfiguration().applyPermitDefaultValues();
              corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
              corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE",
                  "OPTIONS",
                  "HEAD", "TRACE", "CONNECT"));
              corsConfig.setAllowedHeaders(Arrays.asList("*"));
              return corsConfig;
            }))
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/persons").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/token").permitAll()
            .requestMatchers(WHITELIST).permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
