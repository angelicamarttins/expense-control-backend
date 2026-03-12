package com.angelicamartins.expensecontrol.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-control/category")
public class CategoryController {

  @PostMapping
  public String createCategory(String greeting) {
    System.out.println(greeting);
    return greeting;
  }

}
