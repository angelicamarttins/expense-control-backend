package com.angelicamartins.expensecontrol.model.enums;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
public enum PageableExpenseRequest {
  CREATED_AT("createdAt"),
  UPDATED_AT("updatedAt"),
  VALUE("value");

  private final String fieldName;

  PageableExpenseRequest(String fieldName) {
    this.fieldName = fieldName;
  }

  public static PageRequest toPageRequest(int page, int size, Sort sort) {
    return PageRequest.of(page, size, sort);
  }
}
