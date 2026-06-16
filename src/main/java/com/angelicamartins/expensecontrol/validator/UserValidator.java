package com.angelicamartins.expensecontrol.validator;

import com.angelicamartins.expensecontrol.exception.EmptyDtoException;
import com.angelicamartins.expensecontrol.exception.UserNotFound;
import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.model.dto.UserRequestUpdateDto;
import com.angelicamartins.expensecontrol.repository.UserRepository;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
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

  @SneakyThrows
  public void validateUpdateRequest(UserRequestUpdateDto userRequestUpdateDto) {
    int nullValues = 0;
    List<String> keys = Arrays
      .stream(UserRequestUpdateDto.class.getRecordComponents())
      .map(RecordComponent::getName)
      .toList();

    for (RecordComponent component : userRequestUpdateDto.getClass().getRecordComponents()) {
      for (String key : keys) {
        if (component.getName().equals(key) && component.getAccessor().invoke(userRequestUpdateDto) == null) {
          nullValues++;
        }
      }
    }

    System.out.println(nullValues == userRequestUpdateDto.getClass().getRecordComponents().length);

    boolean isThereAtLeastOneField = nullValues == userRequestUpdateDto.getClass().getRecordComponents().length;

    System.out.println(isThereAtLeastOneField);

    if (isThereAtLeastOneField) {
      throw new EmptyDtoException(userRequestUpdateDto.getClass().getName());
    }
  }
}
