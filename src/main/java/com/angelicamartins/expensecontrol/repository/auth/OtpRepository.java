package com.angelicamartins.expensecontrol.repository.auth;

import com.angelicamartins.expensecontrol.model.auth.Otp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OtpRepository extends JpaRepository<Otp, UUID> {

  @Query("""
    SELECT o FROM Otp o
    WHERE o.user.userId = :userId
      AND o.tokenExpiresAt > :now
      AND o.otpRetries <= 4
      AND o.value IS NOT NULL
      AND o.consumedAt IS NOT NULL
      AND o.token IS NOT NULL
      AND o.tokenConsumedAt IS NULL
    ORDER BY o.createdAt DESC
    LIMIT 1
    """)
  Optional<Otp> findToken(UUID userId, Long now);

  @Query("""
    SELECT o FROM Otp o
    WHERE o.user.userId = :userId
      AND o.expiresAt > :now
      AND o.value IS NOT NULL
      AND o.consumedAt IS NULL
    ORDER BY o.createdAt DESC
    LIMIT 1
    """)
  Optional<Otp> findValidOtp(UUID userId, Long now);

  @Modifying
  @Transactional
  @Query("""
    UPDATE Otp o
    SET o.consumedAt = :now, o.updatedAt = :now
    WHERE o.user.userId = :userId
      AND o.value IS NOT NULL
      AND o.consumedAt IS NULL
    """)
  void invalidateActiveOtps(UUID userId, LocalDateTime now);

  @Modifying
  @Transactional
  @Query("""
    UPDATE Otp o
    SET o.tokenConsumedAt = :now, o.updatedAt = :now
    WHERE o.user.userId = :userId
      AND o.value IS NOT NULL
      AND o.consumedAt IS NOT NULL
      AND o.token IS NOT NULL
      AND o.tokenConsumedAt IS NULL
    """)
  void invalidateActiveTokens(UUID userId, LocalDateTime now);
}
