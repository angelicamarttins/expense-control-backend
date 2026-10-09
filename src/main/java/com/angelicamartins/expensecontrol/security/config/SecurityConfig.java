package com.angelicamartins.expensecontrol.security.config;

import com.password4j.AlgorithmFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain web(HttpSecurity http) {
    http
      .authorizeHttpRequests(
        (authorize) ->
          authorize
            .anyRequest()
            .permitAll()
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2Password4jPasswordEncoder(
      AlgorithmFinder.getArgon2Instance()
    );
  }

}
