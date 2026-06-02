package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.dto.UserDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.UserDto.fromRequestDto;

import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.model.dto.UserDto;
import com.angelicamartins.expensecontrol.model.dto.UserRequestDto;
import com.angelicamartins.expensecontrol.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto saveUser(UserRequestDto userRequestDto) {
    User savedUser = userRepository.save(fromRequestDto(userRequestDto));

    return fromEntity(savedUser);
  }
}
