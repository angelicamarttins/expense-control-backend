package com.angelicamartins.expensecontrol.advice;

import com.angelicamartins.expensecontrol.exception.GeneralHttpException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(GeneralHttpException.class)
  public ProblemDetail handleUserNotFound(GeneralHttpException ex) throws URISyntaxException {

    ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

    // type (recomendado)
    problem.setType(URI.create("https://www.angelicamartins.com.br/problems/user-not-found"));

    // title (resumo fixo)
    problem.setTitle("Usuário não encontrado");

    // detail (mensagem específica)
    problem.setDetail("Usuário com ID " + 123 + " não existe");

    // extensions (dados estruturados)
    problem.setProperty("userId", 123);

    problem.setInstance(new URI("recoreco" + UUID.randomUUID()));

    return problem;
  }
}
