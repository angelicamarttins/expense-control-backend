package com.angelicamartins.expensecontrol.security.listeners;

import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.service.user.UserService;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class SuccessLoginListener {

  private final UserService userService;
  private final UserValidator userValidator;

  @EventListener
  public void onLoginSuccess(AuthenticationSuccessEvent successEvent) {
    try {
      UserDto user = userValidator.validateAndReturnUserByEmail(successEvent.getAuthentication().getName());

      if (user.getLoginRetries() > 0) {
        userService.updateLoginRetries(user, 0L);
      }

    } catch (Exception exception) {
      log.error("Something went wrong in login success event");
    }
  }

}
