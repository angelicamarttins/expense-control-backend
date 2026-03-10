package com.angelica_martins.expense_control.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-control/category")
public class CategoryController {

  @PostMapping
  public String createCategory(String greeting) {
    return greeting;
  }

}
