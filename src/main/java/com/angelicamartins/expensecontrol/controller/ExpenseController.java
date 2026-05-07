package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.ExpenseDto;
import com.angelicamartins.expensecontrol.model.dto.ExpenseRequestDto;
import com.angelicamartins.expensecontrol.service.ExpenseService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/{expenseId}")
  public ResponseEntity<ExpenseDto> findExpense(@PathVariable UUID expenseId) {
    return ResponseEntity.ok(expenseService.getExpense(expenseId));
  }

  @GetMapping
  public ResponseEntity<List<ExpenseDto>> findExpenses(
    @RequestParam(defaultValue = "1") int page,
    @RequestParam(defaultValue = "10") int size,
    @RequestParam(defaultValue = "ASC") Sort sort
  ) {
    PageRequest pageRequest = PageRequest.of(page, size, sort);

    return ResponseEntity.ok(expenseService.getExpenses(pageRequest));
  }

}
