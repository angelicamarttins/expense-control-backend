package com.angelicamartins.expensecontrol.validator.expense;

import com.angelicamartins.expensecontrol.exception.expense.ExpenseNotFound;
import com.angelicamartins.expensecontrol.model.expense.Expense;
import com.angelicamartins.expensecontrol.repository.expense.ExpenseRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class ExpenseValidator {

  private final ExpenseRepository expenseRepository;

  public Expense validateAndReturnExpense(UUID expenseId) {
    return expenseRepository
      .findById(expenseId)
      .orElseThrow(() -> new ExpenseNotFound(expenseId));
  }

}
