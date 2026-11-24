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
public class OtpEmailSender {

  private final Resend resend;
  private final TemplateEngine templateEngine;

  public void sendOtp(String rawOtp, UserDto user) {
    Context context = new Context();
    context.setVariable("otp", rawOtp);
    context.setVariable("userFirstName", user.getFirstName());
    String html = templateEngine.process("otp", context);

    CreateEmailOptions params = CreateEmailOptions
      .builder()
      .from("Expense Control App <onboarding@resend.dev>")
      .to(user.getEmail())
      .subject("Redefinição de senha")
      .html(html)
      .build();

    try {
      resend.emails().send(params);
    } catch (ResendException e) {
      throw new RuntimeException(e);
    }
  }

}
