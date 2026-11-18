package com.angelicamartins.expensecontrol.security.otp;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

@Component
public class OtpGenerator {

  private static final SecureRandom RANDOM = new SecureRandom();

  public String generate() {
    int code = RANDOM.nextInt(1_000_000);
    return String.format("%06d", code);
  }

}
