package com.angelicamartins.expensecontrol.validator.category;

import com.angelicamartins.expensecontrol.exception.category.CategoryNotFound;
import com.angelicamartins.expensecontrol.model.category.Category;
import com.angelicamartins.expensecontrol.repository.category.CategoryRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class CategoryValidator {

  private final CategoryRepository categoryRepository;

  public Category validateAndReturnCategory(UUID categoryId) {
    return categoryRepository
      .findById(categoryId)
      .orElseThrow(() -> new CategoryNotFound(categoryId));
  }

}
