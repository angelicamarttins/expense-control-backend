package com.angelicamartins.expensecontrol.model.dto.auth;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import com.angelicamartins.expensecontrol.model.auth.Otp;
import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.model.user.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpDto {

  private UUID otpId;
  private UUID userId;
  private String value;
  private Integer otpRetries;
  private Long expiresAt;
  private LocalDateTime consumedAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String token;
  private Long tokenExpiresAt;
  private LocalDateTime tokenConsumedAt;

  public static OtpDto fromEntity(Otp otp) {
    return OtpDto
      .builder()
      .otpId(otp.getOtpId())
      .userId(otp.getUser().getUserId())
      .value(otp.getValue())
      .otpRetries(otp.getOtpRetries())
      .expiresAt(otp.getExpiresAt())
      .consumedAt(otp.getConsumedAt())
      .createdAt(otp.getCreatedAt())
      .updatedAt(otp.getUpdatedAt())
      .token(otp.getToken())
      .tokenExpiresAt(otp.getTokenExpiresAt())
      .tokenConsumedAt(otp.getTokenConsumedAt())
      .build();
  }

  public static Otp toEntity(OtpDto otpDto) {
    return Otp
      .builder()
      .otpId(otpDto.getOtpId())
      .user(User.builder().userId(otpDto.getUserId()).build())
      .value(otpDto.getValue())
      .otpRetries(otpDto.getOtpRetries())
      .expiresAt(otpDto.getExpiresAt())
      .consumedAt(otpDto.getConsumedAt())
      .createdAt(otpDto.getCreatedAt())
      .updatedAt(otpDto.getUpdatedAt())
      .token(otpDto.getToken())
      .tokenExpiresAt(otpDto.getTokenExpiresAt())
      .tokenConsumedAt(otpDto.getTokenConsumedAt())
      .build();
  }

  public static Otp toEntity(
    String value,
    UserDto user,
    Integer retries,
    Long expiresAt,
    LocalDateTime createdAt
  ) {
    return Otp
      .builder()
      .value(requireNonNull(value, "value é obrigatório"))
      .otpRetries(requireNonNullElse(retries, 0))
      .user(UserDto.toEntity(user))
      .createdAt(requireNonNullElse(createdAt, LocalDateTime.now()))
      .expiresAt(requireNonNull(expiresAt, "expiresAt é obrigatório"))
      .build();
  }

}
