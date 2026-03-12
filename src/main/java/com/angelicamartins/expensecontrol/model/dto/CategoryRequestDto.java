package com.angelicamartins.expensecontrol.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDto(
  @NotBlank
  String categoryName) {
}
