package com.angelicamartins.expensecontrol.exception;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.EXPENSE_NOT_FOUND;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;
import java.util.UUID;

public class ExpenseNotFound extends GeneralHttpException {
  public ExpenseNotFound(UUID expenseId) {
    super(EXPENSE_NOT_FOUND.completeProblemDetail("expenseId: " + expenseId));
  }
}
