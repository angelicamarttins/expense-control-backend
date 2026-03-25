package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.CategoryBuilder.fromEntity;
import static com.angelicamartins.expensecontrol.model.CategoryBuilder.fromRequestDto;

import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.model.CategoryBuilder;
import com.angelicamartins.expensecontrol.model.dto.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import com.angelicamartins.expensecontrol.repository.CategoryRepository;
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

  public CategoryDto saveCategory(CategoryRequestDto  categoryRequestDto) {
    Category savedCategory = categoryRepository.save(fromRequestDto(categoryRequestDto));

    return fromEntity(savedCategory);
  }

  public CategoryDto getCategory(UUID categoryId) {
    Category category = categoryRepository.findById(categoryId).orElseThrow(RuntimeException::new);

    return fromEntity(category);
  }

  public List<CategoryDto> getCategories(Pageable pageable) {
    Slice<Category> categories = categoryRepository.findAllBy(pageable);

    return categories.map(CategoryBuilder::fromEntity).getContent();
  }

  public void deleteCategory(UUID categoryId) {
    categoryRepository.findById(categoryId).orElseThrow(RuntimeException::new);

    categoryRepository.deleteById(categoryId);
  }

}
