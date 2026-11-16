package com.angelicamartins.expensecontrol.validator.user;

import com.angelicamartins.expensecontrol.exception.user.UserNotFound;
import com.angelicamartins.expensecontrol.model.user.User;
import com.angelicamartins.expensecontrol.repository.user.UserRepository;
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

  public User validateAndReturnUserByEmail(String userEmail) {
    return userRepository
      .findByEmail(userEmail)
      .orElseThrow(() -> new UserNotFound(userEmail));
  }

}
