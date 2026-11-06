package com.angelicamartins.expensecontrol.security.config;

import com.password4j.AlgorithmFinder;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain web(HttpSecurity http) {
    http
      .httpBasic(Customizer.withDefaults())
      .cors(Customizer.withDefaults())
      .csrf(csrf -> csrf
        .ignoringRequestMatchers("/auth/signup", "/auth/csrf")
      )
      .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(
        (authorize) ->
          authorize
            .requestMatchers("/auth/**", "/error").permitAll()
            .requestMatchers("/expense-control/**").authenticated()
            .anyRequest().denyAll()
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2Password4jPasswordEncoder(
      AlgorithmFinder.getArgon2Instance()
    );
  }

  @Bean
  public UrlBasedCorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedHeaders(List.of("X-ANY-HEADER", "Content-Type"));
    configuration.setAllowCredentials(Boolean.TRUE);
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

}
