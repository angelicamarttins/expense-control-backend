package com.angelicamartins.expensecontrol.service;

import com.angelicamartins.expensecontrol.model.dto.ExpenseDto;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

  public ExpenseDto saveExpense() {
    return new ExpenseDto();
  }

}
