package com.angelicamartins.expensecontrol.notification.email;

import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
@AllArgsConstructor
public class AuthEmailSender {

  private final Resend resend;
  private final TemplateEngine templateEngine;

  public void sendTooManyLoginRetries(UserDto user) {
    Context context = new Context();
    context.setVariable("userFirstName", user.getFirstName());
    String html = templateEngine.process("too-many-login-retries", context);

    CreateEmailOptions params = CreateEmailOptions
      .builder()
      .from("Expense Control App <onboarding@resend.dev>")
      .to(user.getEmail())
      .subject("Segurança - Troque sua senha")
      .html(html)
      .build();

    try {
      resend.emails().send(params);
    } catch (
      ResendException e) {
      throw new RuntimeException(e);
    }
  }

}
