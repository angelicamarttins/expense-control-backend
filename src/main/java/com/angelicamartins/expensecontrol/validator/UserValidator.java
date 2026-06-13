package com.angelicamartins.expensecontrol.validator;

import com.angelicamartins.expensecontrol.exception.UserNotFound;
import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.repository.UserRepository;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class UserValidator {

  private final UserRepository userRepository;

  public User validateAndReturnUser(UUID userId) {
    return userRepository
      .findById(userId)
      .orElseThrow(() -> new UserNotFound(userId));
  }
}
