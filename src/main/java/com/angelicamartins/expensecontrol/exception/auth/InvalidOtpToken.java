package com.angelicamartins.expensecontrol.exception.auth;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.INVALID_OTP_TOKEN;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;

public class InvalidOtpToken extends GeneralHttpException {
  public InvalidOtpToken() {
    super(INVALID_OTP_TOKEN.simpleProblemDetail());
  }
}
