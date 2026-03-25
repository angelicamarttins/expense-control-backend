package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import com.angelicamartins.expensecontrol.service.CategoryService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/expense-control/category")
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping
  public CategoryDto createCategory(@Validated @RequestBody CategoryRequestDto categoryRequestDto) {
    return categoryService.saveCategory(categoryRequestDto);
  }

  @GetMapping("/{categoryId}")
  public CategoryDto getCategory(@PathVariable UUID categoryId) {
    return categoryService.getCategory(categoryId);
  }

  @GetMapping
  public List<CategoryDto> getCategories(Pageable pageable) {
    return categoryService.getCategories(pageable);
  }

  @DeleteMapping("/{categoryId}")
  public void deleteCategory(@PathVariable UUID categoryId) {
    categoryService.deleteCategory(categoryId);
  }

  @PatchMapping("/{categoryId}")
  public CategoryDto updateCategory(
    @PathVariable UUID categoryId,
    @Validated @RequestBody CategoryRequestDto categoryRequestDto
  ) {
    return categoryService.updateCategory(categoryId, categoryRequestDto);
  }

}
