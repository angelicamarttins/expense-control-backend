package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.service.PasswordService.encodePassword;

import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.model.dto.ResetPasswordDto;
import com.angelicamartins.expensecontrol.validator.UserValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final UserService userService;
  private final UserValidator userValidator;

  public void resetPassword(ResetPasswordDto.Request request) {
    User user = userValidator.validateAndReturnUserByEmail(request.userEmail());
    String encodedPassword = encodePassword(request.newPassword());

    userService.updatePassword(user, encodedPassword);
  }
}
