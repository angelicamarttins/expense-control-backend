package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.dto.UserDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.UserDto.fromRequestDto;

import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.model.dto.UserDto;
import com.angelicamartins.expensecontrol.model.dto.UserRequestDto;
import com.angelicamartins.expensecontrol.repository.UserRepository;
import com.angelicamartins.expensecontrol.validator.UserValidator;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserValidator userValidator;

  public UserDto saveUser(UserRequestDto userRequestDto) {
    User savedUser = userRepository.save(fromRequestDto(userRequestDto));

    return fromEntity(savedUser);
  }

  public List<UserDto> findUsers(Pageable pageable) {
    Slice<User> users = userRepository.findAllBy(pageable);

    return users.map(UserDto::fromEntity).getContent();
  }

  public UserDto findUser(UUID userId) {
    User user = userValidator.validateAndReturnUser(userId);

    return fromEntity(user);
  }

}
