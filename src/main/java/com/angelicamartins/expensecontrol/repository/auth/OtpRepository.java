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
      AND o.expiresAt > :now
      AND o.otpRetries <= 4
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
      AND o.consumedAt IS NULL
    """)
  void invalidateActiveOtps(UUID userId, LocalDateTime now);
}
