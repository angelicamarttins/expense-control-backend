package com.angelicamartins.expensecontrol.exception.auth;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.INVALID_OTP;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;

public class InvalidOtp extends GeneralHttpException {
  public InvalidOtp() {
    super(INVALID_OTP.simpleProblemDetail());
  }
}
