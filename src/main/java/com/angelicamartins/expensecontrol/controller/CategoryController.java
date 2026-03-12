package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.CategoryRequestDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-control/category")
public class CategoryController {

  @PostMapping
  public String createCategory(@Validated @RequestBody CategoryRequestDto categoryRequestDto) {
    return categoryRequestDto.categoryName();
  }

}
