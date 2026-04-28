package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.dto.ExpenseDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.ExpenseDto.fromRequestDto;

import com.angelicamartins.expensecontrol.model.Expense;
import com.angelicamartins.expensecontrol.model.dto.ExpenseDto;
import com.angelicamartins.expensecontrol.model.dto.ExpenseRequestDto;
import com.angelicamartins.expensecontrol.repository.ExpenseRepository;
import com.angelicamartins.expensecontrol.validator.CategoryValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final CategoryValidator categoryValidator;

  public ExpenseDto saveExpense(ExpenseRequestDto expenseRequestDto) {
    categoryValidator.validateAndReturnCategory(expenseRequestDto.categoryId());

    Expense savedExpense = expenseRepository.save(fromRequestDto(expenseRequestDto));

    return fromEntity(savedExpense);
  }

}
