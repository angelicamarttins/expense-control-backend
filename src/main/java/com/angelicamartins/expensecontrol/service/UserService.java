package com.angelicamartins.expensecontrol.service;

import static com.angelicamartins.expensecontrol.model.dto.UserDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.UserDto.fromRequestDto;
import static com.angelicamartins.expensecontrol.service.PasswordService.encodePassword;

import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.model.dto.UserDto;
import com.angelicamartins.expensecontrol.model.dto.UserRequestDto;
import com.angelicamartins.expensecontrol.model.dto.UserRequestUpdateDto;
import com.angelicamartins.expensecontrol.repository.UserRepository;
import com.angelicamartins.expensecontrol.validator.UserValidator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserValidator userValidator;

  public UserDto saveUser(UserRequestDto userRequestDto) {
    String encodedPassword = encodePassword(userRequestDto.password());
    return fromEntity(userRepository.save(fromRequestDto(userRequestDto, encodedPassword)));
  }

  public List<UserDto> findUsers(Pageable pageable) {
    return userRepository
      .findAllBy(pageable)
      .map(UserDto::fromEntity)
      .getContent();
  }

  public UserDto findUser(UUID userId) {
    return fromEntity(userValidator.validateAndReturnUser(userId));
  }

  public UserDto findUserByEmail(String userEmail) {
    return fromEntity(userValidator.validateAndReturnUserByEmail(userEmail));
  }

  public UserDto updateUser(UUID userId, UserRequestUpdateDto userRequestUpdateDto) {
    User user = userValidator.validateAndReturnUser(userId);

    user.setUpdatedAt(LocalDateTime.now());

    if (Objects.nonNull(userRequestUpdateDto.firstName())) {
      user.setFirstName(userRequestUpdateDto.firstName());
    }

    if (Objects.nonNull(userRequestUpdateDto.lastName())) {
      user.setLastName(userRequestUpdateDto.lastName());
    }

    if (Objects.nonNull(userRequestUpdateDto.email())) {
      user.setEmail(userRequestUpdateDto.email());
    }

    return fromEntity(userRepository.save(user));
  }

  public UserDto updatePassword(User user, String newPassword) {
    user.setPassword(newPassword);
    user.setUpdatedAt(LocalDateTime.now());

    return fromEntity(userRepository.save(user));
  }

}
