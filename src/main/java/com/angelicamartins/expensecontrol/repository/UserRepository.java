package com.angelicamartins.expensecontrol.repository;

import com.angelicamartins.expensecontrol.model.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
}
