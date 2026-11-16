package com.angelicamartins.expensecontrol.repository.expense;

import com.angelicamartins.expensecontrol.model.expense.Expense;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
