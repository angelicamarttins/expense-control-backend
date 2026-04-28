package com.angelicamartins.expensecontrol.repository;

import com.angelicamartins.expensecontrol.model.Expense;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, UUID> {
}
