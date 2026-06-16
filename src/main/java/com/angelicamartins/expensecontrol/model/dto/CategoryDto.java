package com.angelicamartins.expensecontrol.model.dto;

import com.angelicamartins.expensecontrol.model.Category;
import java.time.LocalDateTime;
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
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

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
    return CategoryDto.builder()
      .categoryId(category.getCategoryId())
      .categoryName(category.getCategoryName())
      .defaultCategory(category.getDefaultCategory())
      .createdAt(category.getCreatedAt())
      .updatedAt(category.getUpdatedAt())
      .build();
  }
}
