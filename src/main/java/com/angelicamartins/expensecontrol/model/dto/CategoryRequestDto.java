package com.angelicamartins.expensecontrol.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(@NotBlank String categoryName) {
}
