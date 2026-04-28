package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.ExpenseDto;
import com.angelicamartins.expensecontrol.model.dto.ExpenseRequestDto;
import com.angelicamartins.expensecontrol.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("expense-control/expense")
public class ExpenseController {

  private final ExpenseService expenseService;

  @PostMapping
  public ResponseEntity<ExpenseDto> createExpense(@Valid @RequestBody ExpenseRequestDto expenseRequestDto) {
    return ResponseEntity.ok(expenseService.saveExpense(expenseRequestDto));
  }

}
