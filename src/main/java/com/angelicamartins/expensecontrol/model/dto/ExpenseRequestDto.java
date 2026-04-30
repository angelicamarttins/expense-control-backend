package com.angelicamartins.expensecontrol.model.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ExpenseRequestDto(
  String description,
  @NotNull
  @Digits(integer = 13, fraction = 2)
  @DecimalMax("1000000000000")
  BigDecimal value,
  @NotNull
  UUID categoryId
) {
}
