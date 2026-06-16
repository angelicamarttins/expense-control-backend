package com.angelicamartins.expensecontrol.model.dto;

import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.model.Expense;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseDto {

  private UUID expenseId;
  private String description;
  private BigDecimal value;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
  private Category category;

  public static Expense fromRequestDto(ExpenseRequestDto expenseRequestDto, Category category) {
    return Expense.builder()
      .description(expenseRequestDto.description())
      .value(expenseRequestDto.value())
      .createdAt(LocalDateTime.now())
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
      .category(expense.getCategory())
      .build();
  }

}
