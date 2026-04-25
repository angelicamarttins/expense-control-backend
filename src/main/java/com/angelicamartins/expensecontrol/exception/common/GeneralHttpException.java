package com.angelicamartins.expensecontrol.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Getter
public class GeneralHttpException extends RuntimeException {

  private final ProblemDetail problemDetail;
  private final HttpStatus httpStatus;

  public GeneralHttpException(ProblemDetail problemDetail, Throwable cause) {
    super(problemDetail.getTitle(), cause);

    this.problemDetail = problemDetail;
    this.httpStatus = HttpStatus.valueOf(problemDetail.getStatus());
  }

  public GeneralHttpException(ProblemDetail problemDetail) {
    this(problemDetail, null);
  }

}
