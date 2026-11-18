package com.angelicamartins.expensecontrol.service.auth;

import static com.angelicamartins.expensecontrol.model.auth.Otp.toEntity;

import com.angelicamartins.expensecontrol.model.dto.auth.ForgotPasswordRequestDto;
import com.angelicamartins.expensecontrol.model.dto.auth.VerifyOtpDto;
import com.angelicamartins.expensecontrol.model.user.User;
import com.angelicamartins.expensecontrol.notification.email.OtpEmailSender;
import com.angelicamartins.expensecontrol.repository.auth.OtpRepository;
import com.angelicamartins.expensecontrol.security.otp.OtpGenerator;
import com.angelicamartins.expensecontrol.security.otp.OtpHasher;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
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
    User user = userValidator.validateAndReturnUserByEmail(requestDto.userEmail());

    String rawOtp = otpGenerator.generate();
    otpRepository.save(toEntity(otpHasher.hash(rawOtp), user, 0));

    otpEmailSender.send(rawOtp, user);
  }

  public void matchOtp(VerifyOtpDto.Request requestDto) {
    // request tem que ter email
    // buscar user id
    // obter horário atual, transformar em millis e enviar para query para achar registro com mesmo user id e expires at menor
    // query para buscar otp com menos de 5 minutos com o user id
    // se estiver expirado, passou da quantidade de tentativas, otp não encontrado, criar expection genérica de otp inválido
    // encontrou e está dentro do prazo de expiração (isso já será validado ao ser encontrado), validar quantidade de tentativas
    // tudo certo, calcular hash do otp
    // comparar com hash presente no banco
    // inválido se não for o mesmo otp
    // gerar token
    // retornar token e finalizar fluxo
  }

}
