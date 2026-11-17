package com.angelicamartins.expensecontrol.model.auth;

import static java.util.Objects.nonNull;

import com.angelicamartins.expensecontrol.model.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "otp")
public class Otp {

  @Id
  @GeneratedValue
  @Column(name = "otp_id", nullable = false, updatable = false)
  private UUID otpId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false, updatable = false)
  private User user;

  @Column(name = "value", nullable = false, updatable = false, length = 64)
  private String value;

  @Builder.Default
  @Column(name = "otp_retries", nullable = false)
  private Integer otpRetries = 0;

  @Column(name = "expires_at", nullable = false)
  private Long expiresAt;

  @Column(name = "consumed_at")
  private LocalDateTime consumedAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "token", length = 64)
  private String token;

  @Column(name = "token_expires_at")
  private Long tokenExpiresAt;

  @Column(name = "token_consumed_at")
  private LocalDateTime tokenConsumedAt;

  public static Otp toEntity(String value, User user, Integer retries) {
    Instant createdAt = Instant.now();
    long expiresAt = createdAt.plus(Duration.ofMinutes(5)).toEpochMilli();

    return Otp
      .builder()
      .value(value)
      .otpRetries(hasRetries(retries))
      .user(user)
      .createdAt(LocalDateTime.ofInstant(createdAt, ZoneId.of("America/Sao_Paulo")))
      .expiresAt(expiresAt)
      .build();
  }

  private static Integer hasRetries(Integer retries) {
    return nonNull(retries) ? retries : 0;
  }

}
