package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import com.angelicamartins.expensecontrol.service.CategoryService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<CategoryDto> createCategory(@Validated @RequestBody CategoryRequestDto categoryRequestDto) {
    return ResponseEntity.ok(categoryService.saveCategory(categoryRequestDto));
  }

  @GetMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> getCategory(@PathVariable UUID categoryId) {
    return ResponseEntity.ok(categoryService.getCategory(categoryId));
  }

  @GetMapping
  public ResponseEntity<List<CategoryDto>> getCategories(Pageable pageable) {
    return ResponseEntity.ok(categoryService.getCategories(pageable));
  }

  @DeleteMapping("/{categoryId}")
  public ResponseEntity<Void> deleteCategory(@PathVariable UUID categoryId) {
    categoryService.deleteCategory(categoryId);

    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/{categoryId}")
  public ResponseEntity<CategoryDto> updateCategory(
    @PathVariable UUID categoryId,
    @Validated @RequestBody CategoryRequestDto categoryRequestDto
  ) {
    return ResponseEntity.ok(categoryService.updateCategory(categoryId, categoryRequestDto));
  }

}
