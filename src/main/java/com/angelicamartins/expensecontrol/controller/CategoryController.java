package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.CategoryDto;
import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import com.angelicamartins.expensecontrol.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
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

}
