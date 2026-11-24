package com.angelicamartins.expensecontrol.notification.email.config;

import com.resend.Resend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ResendConfig {

  @Bean
  public Resend resend(@Value("${resend.apikey}") String apiKey) {
    return new Resend(apiKey);
  }

}
