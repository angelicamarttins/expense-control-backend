package com.angelicamartins.expensecontrol.model.validation;

import com.angelicamartins.expensecontrol.model.validation.validationImpl.AtLeastOneFieldValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneFieldValidation.class)
public @interface AtLeastOneField {

  String message() default "At least one field must has value";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};

}
