package com.angelicamartins.expensecontrol.service.category;

import static com.angelicamartins.expensecontrol.model.dto.category.CategoryDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.category.CategoryDto.fromRequestDto;

import com.angelicamartins.expensecontrol.model.category.Category;
import com.angelicamartins.expensecontrol.model.dto.category.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.category.CategoryRequestDto;
import com.angelicamartins.expensecontrol.repository.category.CategoryRepository;
import com.angelicamartins.expensecontrol.validator.category.CategoryValidator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryValidator categoryValidator;

  public CategoryDto saveCategory(CategoryRequestDto  categoryRequestDto) {
    Category savedCategory = categoryRepository.save(fromRequestDto(categoryRequestDto));

    return fromEntity(savedCategory);
  }

  public CategoryDto getCategory(UUID categoryId) {
    Category category = categoryValidator.validateAndReturnCategory(categoryId);

    return fromEntity(category);
  }

  public List<CategoryDto> getCategories(Pageable pageable) {
    Slice<Category> categories = categoryRepository.findAllBy(pageable);

    return categories.map(CategoryDto::fromEntity).getContent();
  }

  public void deleteCategory(UUID categoryId) {
    categoryValidator.validateAndReturnCategory(categoryId);

    categoryRepository.deleteById(categoryId);
  }

  public CategoryDto updateCategory(UUID categoryId, CategoryRequestDto categoryRequestDto) {
    Category category = categoryValidator.validateAndReturnCategory(categoryId);

    category.setCategoryName(categoryRequestDto.categoryName());
    category.setUpdatedAt(LocalDateTime.now());

    categoryRepository.save(category);

    return fromEntity(category);
  }

}
