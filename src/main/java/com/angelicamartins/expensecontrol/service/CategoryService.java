package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.CategoryBuilder.fromEntity;
import static com.angelicamartins.expensecontrol.model.CategoryBuilder.fromRequestDto;

import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.model.dto.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import com.angelicamartins.expensecontrol.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
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

}
