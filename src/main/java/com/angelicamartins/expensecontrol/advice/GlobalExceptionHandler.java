package com.angelicamartins.expensecontrol.advice;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(GeneralHttpException.class)
  public ResponseEntity<ProblemDetail> handleGeneralHttpException(GeneralHttpException ex) {
    ProblemDetail pd = ex.getProblemDetail();

    return ResponseEntity
      .status(pd.getStatus())
      .body(pd);
  }
}
