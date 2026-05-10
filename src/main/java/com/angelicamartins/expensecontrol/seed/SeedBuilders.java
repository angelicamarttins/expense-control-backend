package com.angelicamartins.expensecontrol.seed;

import com.angelicamartins.expensecontrol.model.Category;
import java.time.ZonedDateTime;
import java.util.UUID;

public class SeedBuilders {

  public static Category buildDefaultCategory(UUID categoryId, String categoryName) {
    return Category
      .builder()
      .categoryId(categoryId)
      .categoryName(categoryName)
      .defaultCategory(true)
      .createdAt(ZonedDateTime.now())
      .build();
  }

}
