package com.angelicamartins.expensecontrol.validator.user;

import static com.angelicamartins.expensecontrol.model.dto.user.UserDto.fromEntity;

import com.angelicamartins.expensecontrol.exception.user.UserNotFound;
import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
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

  public UserDto validateAndReturnUser(UUID userId) {
    return fromEntity(userRepository
      .findById(userId)
      .orElseThrow(() -> new UserNotFound(userId)));
  }

  public UserDto validateAndReturnUserByEmail(String userEmail) {
    return fromEntity(userRepository
      .findByEmail(userEmail)
      .orElseThrow(() -> new UserNotFound(userEmail)));
  }

}
