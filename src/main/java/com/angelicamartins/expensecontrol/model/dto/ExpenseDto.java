package com.angelicamartins.expensecontrol.model.dto;

import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.model.Expense;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseDto {

  private UUID expenseId;
  private String description;
  private BigDecimal value;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;
  private ZonedDateTime deletedAt;
  private Category category;

  public static Expense fromRequestDto(ExpenseRequestDto expenseRequestDto, Category category) {
    return Expense.builder()
      .description(expenseRequestDto.description())
      .value(expenseRequestDto.value())
      .createdAt(ZonedDateTime.now())
      .category(category)
      .build();
  }

  public static ExpenseDto fromEntity(Expense expense) {
    return ExpenseDto.builder()
      .expenseId(expense.getExpenseId())
      .description(expense.getDescription())
      .value(expense.getValue())
      .createdAt(expense.getCreatedAt())
      .updatedAt(expense.getUpdatedAt())
      .deletedAt(expense.getDeletedAt())
      .category(expense.getCategory())
      .build();
  }

}
