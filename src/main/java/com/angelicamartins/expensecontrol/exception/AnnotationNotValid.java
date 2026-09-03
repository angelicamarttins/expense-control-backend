package com.angelicamartins.expensecontrol.exception;

import static com.angelicamartins.expensecontrol.exception.common.ProblemDetailType.ANNOTATION_NOT_VALID;

import com.angelicamartins.expensecontrol.exception.common.GeneralHttpException;

public class AnnotationNotValid extends GeneralHttpException {
  public AnnotationNotValid() {
    super(ANNOTATION_NOT_VALID.simpleProblemDetail());
  }
}
