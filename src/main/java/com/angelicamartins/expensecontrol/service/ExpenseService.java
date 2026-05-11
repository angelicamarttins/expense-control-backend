package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.dto.ExpenseDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.ExpenseDto.fromRequestDto;

import com.angelicamartins.expensecontrol.model.Category;
import com.angelicamartins.expensecontrol.model.Expense;
import com.angelicamartins.expensecontrol.model.dto.ExpenseDto;
import com.angelicamartins.expensecontrol.model.dto.ExpenseRequestDto;
import com.angelicamartins.expensecontrol.repository.ExpenseRepository;
import com.angelicamartins.expensecontrol.validator.CategoryValidator;
import com.angelicamartins.expensecontrol.validator.ExpenseValidator;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseService {

  private final ExpenseRepository expenseRepository;
  private final ExpenseValidator expenseValidator;
  private final CategoryValidator categoryValidator;

  public ExpenseDto saveExpense(ExpenseRequestDto expenseRequestDto) {
    Category category = categoryValidator.validateAndReturnCategory(expenseRequestDto.categoryId());

    Expense savedExpense = expenseRepository.save(fromRequestDto(expenseRequestDto, category));

    return fromEntity(savedExpense);
  }

  public ExpenseDto getExpense(UUID expenseId) {
    Expense savedExpense = expenseValidator.validateAndReturnExpense(expenseId);

    return fromEntity(savedExpense);
  }

  public List<ExpenseDto> getExpenses(PageRequest pageRequest) {
    return expenseRepository.findAll(pageRequest).stream().map(ExpenseDto::fromEntity).toList();
  }

  public void deleteExpense(UUID expenseId) {
    expenseValidator.validateAndReturnExpense(expenseId);

    expenseRepository.deleteById(expenseId);
  }

}
