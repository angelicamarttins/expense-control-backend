package com.angelicamartins.expensecontrol.model.validation.validationImpl;

import com.angelicamartins.expensecontrol.exception.AnnotationNotValid;
import com.angelicamartins.expensecontrol.exception.EmptyDtoException;
import com.angelicamartins.expensecontrol.model.validation.AtLeastOneField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import lombok.SneakyThrows;

public class AtLeastOneFieldValidation implements ConstraintValidator<AtLeastOneField, Object> {

  @Override
  @SneakyThrows
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }

    int nullValues = 0;
    Class<?> clazz = value.getClass();

    if (!clazz.isRecord()) {
      throw new AnnotationNotValid();
    }

    List<String> componentKeys = Arrays
      .stream(clazz.getRecordComponents())
      .map(RecordComponent::getName)
      .toList();

    for (RecordComponent component : value.getClass().getRecordComponents()) {
      for (String componentKey : componentKeys) {
        if (component.getName().equals(componentKey)) {
          if (component.getAccessor().invoke(value) == null) {
            nullValues++;
          }
        }
        if (component.getAccessor().invoke(value) instanceof String && value.toString().trim().isEmpty()) {
          System.out.println("Oi sou uma string vazia");
          nullValues++;
        }
        if (component.getAccessor().invoke(value) instanceof String str && str.trim().isEmpty()) {
          System.out.println("Oi sou uma string vazia");
          nullValues++;
        }
      }
    }

    System.out.println(nullValues);
    boolean allFieldsAreNull = nullValues == value.getClass().getRecordComponents().length;

    if (allFieldsAreNull) {
      throw new EmptyDtoException(value.getClass().getName());
    }

    return true;
  }

}
