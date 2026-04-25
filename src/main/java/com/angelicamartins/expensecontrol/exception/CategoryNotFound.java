package com.angelicamartins.expensecontrol.exception;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.CATEGORY_NOT_FOUND;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;
import java.util.UUID;

public class CategoryNotFound extends GeneralHttpException {
  public CategoryNotFound(UUID categoryId) {
    super(CATEGORY_NOT_FOUND.completeProblemDetail("categoryId: " + categoryId));
  }
}
