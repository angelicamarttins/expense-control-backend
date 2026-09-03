package com.angelicamartins.expensecontrol.exception.common;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Getter
public enum ProblemDetailType {
  CATEGORY_NOT_FOUND(createUri("category-not-found"), NOT_FOUND, "Category not found"),
  EXPENSE_NOT_FOUND(createUri("expense-not-found"), NOT_FOUND, "Expense not found"),
  USER_NOT_FOUND(createUri("user-not-found"), NOT_FOUND, "User not found"),
  EMPTY_DTO(createUri("empty-dto"), BAD_REQUEST, "Empty DTO. Send at least one field"),
  ANNOTATION_NOT_VALID(createUri("annotation-not-valid"), BAD_REQUEST, "Annotation valid only for Record");

  private static final String BASE_URI_TYPE = "https://www.expense-control.com/problems/";
  private final URI type;
  private final HttpStatus status;
  private final String title;

  ProblemDetailType(URI type, HttpStatus status, String title) {
    this.type = type;
    this.status = status;
    this.title = title;
  }

  @SneakyThrows
  private static URI createUri(String uriType) {
    return new URI(BASE_URI_TYPE + uriType);
  }

  public ProblemDetail completeProblemDetail(String detail) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(this.status, detail);
    problemDetail.setType(this.type);
    problemDetail.setTitle(this.title);

    return problemDetail;
  }

  public ProblemDetail simpleProblemDetail() {
    ProblemDetail problemDetail = ProblemDetail.forStatus(this.status);

    problemDetail.setType(this.type);
    problemDetail.setTitle(this.title);

    return problemDetail;
  }
}
