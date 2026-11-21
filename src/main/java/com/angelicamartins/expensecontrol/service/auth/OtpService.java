package com.angelicamartins.expensecontrol.service.auth;

import static com.angelicamartins.expensecontrol.model.dto.auth.OtpDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.auth.OtpDto.toEntity;

import com.angelicamartins.expensecontrol.exception.auth.InvalidOtp;
import com.angelicamartins.expensecontrol.model.dto.auth.ExpiresAt;
import com.angelicamartins.expensecontrol.model.dto.auth.ForgotPasswordRequestDto;
import com.angelicamartins.expensecontrol.model.dto.auth.OtpDto;
import com.angelicamartins.expensecontrol.model.dto.auth.VerifyOtpDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.notification.email.OtpEmailSender;
import com.angelicamartins.expensecontrol.repository.auth.OtpRepository;
import com.angelicamartins.expensecontrol.security.otp.OtpGenerator;
import com.angelicamartins.expensecontrol.security.otp.OtpHasher;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtpService {

  private final UserValidator userValidator;
  private final OtpEmailSender otpEmailSender;
  private final OtpGenerator otpGenerator;
  private final OtpHasher otpHasher;
  private final OtpRepository otpRepository;

  public void generateAndSendOtp(ForgotPasswordRequestDto requestDto) {
    ExpiresAt expiresAtAndCreatedAt = otpGenerator.generateExpiresAt();

    UserDto user = userValidator.validateAndReturnUserByEmail(requestDto.userEmail());

    otpRepository.invalidateActiveOtps(user.getUserId(), LocalDateTime.now());

    String rawOtp = otpGenerator.generateOtp();

    otpRepository.save(
      OtpDto.toEntity(
        otpHasher.hashOtp(rawOtp),
        user,
        0,
        expiresAtAndCreatedAt.expiresAt(),
        expiresAtAndCreatedAt.createdAt()
      )
    );

    otpEmailSender.send(rawOtp, user);
  }

  public VerifyOtpDto.Response matchOtp(VerifyOtpDto.Request requestDto) {
    UserDto user = userValidator.validateAndReturnUserByEmail(requestDto.userEmail());
    Long now = Instant.now().toEpochMilli();
    LocalDateTime nowAsLocal = otpGenerator.fromMillisToLocalDateTime(now);

    OtpDto otp = fromEntity(otpRepository.findValidOtp(user.getUserId(), now).orElseThrow(InvalidOtp::new));

    try {
      if (!otpHasher.matchesOtp(requestDto.rawOtp(), otp.getValue())) {
        throw new InvalidOtp();
      }
    } catch (InvalidOtp exception) {
      otp.setOtpRetries(incrementOtp(otp.getOtpRetries()));

      otpRepository.save(toEntity(otp));

      throw exception;
    }

    String rawToken = otpGenerator.generateToken();
    String hashedToken = otpHasher.hashToken(rawToken);

    otp.setConsumedAt(nowAsLocal);
    otp.setToken(hashedToken);
    otp.setTokenExpiresAt(otpGenerator.generateExpiresAt().expiresAt());
    otp.setUpdatedAt(nowAsLocal);

    otpRepository.save(toEntity(otp));

    return new VerifyOtpDto.Response(rawToken);
  }

  private Integer incrementOtp(Integer otpRetries) {
    return otpRetries + 1;
  }

}
