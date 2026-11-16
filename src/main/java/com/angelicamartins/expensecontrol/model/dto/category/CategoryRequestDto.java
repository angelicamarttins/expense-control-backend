package com.angelicamartins.expensecontrol.model.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(@NotBlank String categoryName) {
}
