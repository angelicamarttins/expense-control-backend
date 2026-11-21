package com.angelicamartins.expensecontrol.service.user;

import static com.angelicamartins.expensecontrol.model.dto.user.UserDto.fromEntity;
import static com.angelicamartins.expensecontrol.model.dto.user.UserDto.fromRequestDto;
import static com.angelicamartins.expensecontrol.model.dto.user.UserDto.toEntity;
import static com.angelicamartins.expensecontrol.service.auth.PasswordService.encodePassword;

import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserRequestDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserRequestUpdateDto;
import com.angelicamartins.expensecontrol.repository.user.UserRepository;
import com.angelicamartins.expensecontrol.validator.user.UserValidator;
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
    return userValidator.validateAndReturnUser(userId);
  }

  public UserDto findUserByEmail(String userEmail) {
    return userValidator.validateAndReturnUserByEmail(userEmail);
  }

  public UserDto updateUser(UUID userId, UserRequestUpdateDto userRequestUpdateDto) {
    UserDto user = userValidator.validateAndReturnUser(userId);

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

    return fromEntity(userRepository.save(toEntity(user)));
  }

  public void updatePassword(UserDto user, String newPassword) {
    user.setPassword(newPassword);
    user.setUpdatedAt(LocalDateTime.now());

    userRepository.save(toEntity(user));
  }

}
