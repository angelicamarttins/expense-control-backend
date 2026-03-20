package com.angelicamartins.expensecontrol.model;

import com.angelicamartins.expensecontrol.model.dto.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import java.time.LocalDateTime;
import java.util.UUID;

public class CategoryBuilder {

  public static Category fromRequestDto(CategoryRequestDto categoryRequestDto) {
    return Category
      .builder()
      .categoryId(UUID.randomUUID())
      .categoryName(categoryRequestDto.categoryName())
      .defaultCategory(false)
      .createdAt(LocalDateTime.now())
      .build();
  }

  public static CategoryDto fromEntity(Category category) {
    return new CategoryDto(
      category.getCategoryId(),
      category.getCategoryName(),
      category.getDefaultCategory(),
      category.getCreatedAt(),
      category.getUpdatedAt(),
      category.getDeletedAt()
    );
  }

}
