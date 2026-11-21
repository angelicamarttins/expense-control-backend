package com.angelicamartins.expensecontrol.service.auth;

import static com.angelicamartins.expensecontrol.service.auth.PasswordService.encodePassword;

import com.angelicamartins.expensecontrol.model.dto.auth.ResetPasswordDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.service.user.UserService;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final UserService userService;
  private final UserValidator userValidator;

  public void resetPassword(ResetPasswordDto.Request request) {
    UserDto user = userValidator.validateAndReturnUserByEmail(request.userEmail());
    String encodedPassword = encodePassword(request.newPassword());

    userService.updatePassword(user, encodedPassword);
  }
}
