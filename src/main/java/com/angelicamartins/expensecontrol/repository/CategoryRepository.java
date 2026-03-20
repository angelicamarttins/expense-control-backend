package com.angelicamartins.expensecontrol.repository;

import com.angelicamartins.expensecontrol.model.Category;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
