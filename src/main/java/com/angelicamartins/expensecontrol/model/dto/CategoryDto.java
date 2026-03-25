package com.angelicamartins.expensecontrol.model.dto;

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
}
