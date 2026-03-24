package com.angelicamartins.expensecontrol.repository;

import com.angelicamartins.expensecontrol.model.Category;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

  Slice<Category> findAllBy(Pageable pageable);

}
