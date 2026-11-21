package com.angelicamartins.expensecontrol.security.otp;

import com.angelicamartins.expensecontrol.model.dto.auth.ExpiresAt;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class OtpGenerator {

  private static final SecureRandom RANDOM = new SecureRandom();
  private static final int TOKEN_BYTES = 32; // 256 bits de entropia

  public String generateOtp() {
    int code = RANDOM.nextInt(1_000_000);
    return String.format("%06d", code);
  }

  public String generateToken() {
    byte[] bytes = new byte[TOKEN_BYTES];
    RANDOM.nextBytes(bytes);

    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
  }

  public ExpiresAt generateExpiresAt() {
    Instant createdAt = Instant.now();
    long expiresAt = createdAt.plus(Duration.ofMinutes(5)).toEpochMilli();
    LocalDateTime createdAtAsDate = fromInstantToLocalDateTime(createdAt);

    return new ExpiresAt(expiresAt, createdAtAsDate);
  }

  public LocalDateTime fromInstantToLocalDateTime(Instant instant) {
    return LocalDateTime.ofInstant(instant, ZoneId.of("America/Sao_Paulo"));
  }

  public LocalDateTime fromMillisToLocalDateTime(Long milli) {
    if (milli == null) {
      return null;
    }

    return LocalDateTime.ofInstant(Instant.ofEpochMilli(milli), ZoneId.of("America/Sao_Paulo"));
  }

}
