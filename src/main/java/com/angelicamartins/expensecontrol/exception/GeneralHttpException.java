package com.angelicamartins.expensecontrol.exception;

import java.util.Objects;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GeneralHttpException extends RuntimeException {

  private HttpStatus httpStatus;

  public GeneralHttpException(String message, HttpStatus httpStatus, Throwable cause) {
    super(message, cause);

    this.httpStatus = Objects.nonNull(httpStatus) ? httpStatus : HttpStatus.BAD_REQUEST;
  }

  public GeneralHttpException(String message, Throwable cause) {
    this(message, null, cause);
  }

  public GeneralHttpException(String message) {
    this(message, null, null);
  }

}
