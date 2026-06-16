package com.angelicamartins.expensecontrol.exception;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.EMPTY_DTO;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;

public class EmptyDtoException extends GeneralHttpException {
  public EmptyDtoException(String dtoName) {
    super(EMPTY_DTO.completeProblemDetail("DTO: " + dtoName));
  }
}
