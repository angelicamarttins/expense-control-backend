package com.angelicamartins.expensecontrol.validator;

import com.angelicamartins.expensecontrol.exception.CategoryNotFound;
import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.repository.CategoryRepository;
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
