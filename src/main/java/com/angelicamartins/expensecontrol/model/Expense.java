package com.angelicamartins.expensecontrol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
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
@Table(name = "expense")
public class Expense {

  @Id
  @GeneratedValue
  @Column(name = "expense_id", nullable = false, updatable = false)
  private UUID expenseId;

  @Column(name = "description")
  private String description;

  @Column(name = "value")
  private BigDecimal value;

  @Column(name = "created_at", nullable = false, updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;

  @ManyToOne
  private Category category;

}
