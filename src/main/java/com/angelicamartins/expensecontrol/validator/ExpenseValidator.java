package com.angelicamartins.expensecontrol.validator;

import com.angelicamartins.expensecontrol.exception.ExpenseNotFound;
import com.angelicamartins.expensecontrol.model.Expense;
import com.angelicamartins.expensecontrol.repository.ExpenseRepository;
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
