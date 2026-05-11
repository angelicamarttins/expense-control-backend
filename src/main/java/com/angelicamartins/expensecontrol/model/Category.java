package com.angelicamartins.expensecontrol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

  @Id
  @Column(name = "category_id", nullable = false, updatable = false)
  private UUID categoryId;

  @Column(name = "category_name", nullable = false)
  private String categoryName;

  @Column(name = "default_category", nullable = false)
  private Boolean defaultCategory;

  @Column(name = "created_at", nullable = false, updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

}
