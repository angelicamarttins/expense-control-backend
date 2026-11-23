package com.angelicamartins.expensecontrol.service.auth;

import static com.angelicamartins.expensecontrol.model.dto.auth.OtpDto.fromEntity;
import static com.angelicamartins.expensecontrol.service.auth.PasswordService.encodePassword;

import com.angelicamartins.expensecontrol.exception.auth.InvalidOtpToken;
import com.angelicamartins.expensecontrol.model.dto.auth.OtpDto;
import com.angelicamartins.expensecontrol.model.dto.auth.ResetPasswordDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.repository.auth.OtpRepository;
import com.angelicamartins.expensecontrol.security.otp.OtpGenerator;
import com.angelicamartins.expensecontrol.security.otp.OtpHasher;
import com.angelicamartins.expensecontrol.service.user.UserService;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
import java.time.Instant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final OtpGenerator otpGenerator;
  private final OtpHasher otpHasher;
  private final OtpRepository otpRepository;
  private final OtpService otpService;
  private final UserService userService;
  private final UserValidator userValidator;

  public void resetPassword(ResetPasswordDto.Request request) {
    UserDto user = userValidator.validateAndReturnUserByEmail(request.userEmail());
    Long now = Instant.now().toEpochMilli();

    OtpDto otp = fromEntity(
      otpRepository
        .findToken(user.getUserId(), now)
        .orElseThrow(InvalidOtpToken::new)
    );

    boolean tokenMatch = otpHasher.matchesToken(request.token(), otp.getToken());

    otpService.updateOtpTokenConsumedAt(otp, otpGenerator.fromMillisToLocalDateTime(now));

    if (!tokenMatch) {
      throw new InvalidOtpToken();
    }


    String encodedPassword = encodePassword(request.newPassword());

    userService.updatePassword(user, encodedPassword);
  }
}
