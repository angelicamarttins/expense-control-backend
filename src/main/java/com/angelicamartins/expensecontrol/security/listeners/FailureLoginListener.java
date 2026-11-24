package com.angelicamartins.expensecontrol.security.listeners;

import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.notification.email.AuthEmailSender;
import com.angelicamartins.expensecontrol.service.user.UserService;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class FailureLoginListener {

  private final AuthEmailSender authEmailSender;
  private final UserService userService;
  private final UserValidator userValidator;

  @EventListener
  public void onFailureLoginListener(AuthenticationFailureBadCredentialsEvent failureEvent) {
    try {
      UserDto user = userValidator.validateAndReturnUserByEmail(failureEvent.getAuthentication().getName());

      if (user.getLoginRetries() % 10 == 0) {
        authEmailSender.sendTooManyLoginRetries(user);
      }

      userService.updateLoginRetries(user, userService.incrementLoginRetries(user.getLoginRetries()));
    } catch (Exception exception) {
      log.error("Something went wrong in login success event");
    }
  }

}
