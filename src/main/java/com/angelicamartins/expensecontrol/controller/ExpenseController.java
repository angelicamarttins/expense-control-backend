package com.angelicamartins.expensecontrol.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("expense-control/expense")
public class ExpenseController {

  @PostMapping
  public ResponseEntity<HttpStatus> createExpense() {
    return ResponseEntity.ok(CREATED);
  }

}
