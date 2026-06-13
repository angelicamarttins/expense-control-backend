package com.angelicamartins.expensecontrol.repository;

import com.angelicamartins.expensecontrol.model.User;
import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {

  Slice<User> findAllBy(Pageable pageable);

}
