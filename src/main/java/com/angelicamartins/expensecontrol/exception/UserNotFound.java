package com.angelicamartins.expensecontrol.exception;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.USER_NOT_FOUND;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;
import java.util.UUID;

public class UserNotFound extends GeneralHttpException {
  public UserNotFound(UUID userId) {
    super(USER_NOT_FOUND.completeProblemDetail("userId: " + userId));
  }
}
