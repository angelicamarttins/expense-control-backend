package com.angelicamartins.expensecontrol.model.dto;

import com.angelicamartins.expensecontrol.model.Category;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

  private UUID categoryId;
  private String categoryName;
  private Boolean defaultCategory;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

  public static Category fromRequestDto(CategoryRequestDto categoryRequestDto) {
    return Category
      .builder()
      .categoryName(categoryRequestDto.categoryName())
      .defaultCategory(false)
      .createdAt(ZonedDateTime.now())
      .build();
  }

  public static CategoryDto fromEntity(Category category) {
    return CategoryDto.builder()
      .categoryId(category.getCategoryId())
      .categoryName(category.getCategoryName())
      .defaultCategory(category.getDefaultCategory())
      .createdAt(category.getCreatedAt())
      .updatedAt(category.getUpdatedAt())
      .build();
  }
}
