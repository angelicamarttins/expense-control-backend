package com.angelicamartins.expensecontrol.model.dto;

import com.angelicamartins.expensecontrol.model.User;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private UUID userId;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;

  public static UserDto fromEntity(User user) {
    return UserDto
      .builder()
      .userId(user.getUserId())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .email(user.getEmail())
      .password(user.getPassword())
      .createdAt(user.getCreatedAt())
      .updatedAt(user.getUpdatedAt())
      .deletedAt(user.getDeletedAt())
      .build();
  }

  public static User fromRequestDto(UserRequestDto userRequestDto) {
    return User
      .builder()
      .firstName(userRequestDto.firstName())
      .lastName(userRequestDto.lastName())
      .email(userRequestDto.email())
      .password(userRequestDto.password())
      .createdAt(LocalDateTime.now())
      .build();
  }

  public static User fromRequestUpdateDto(UserRequestUpdateDto userRequestUpdateDto) {
    return User
      .builder()
      .firstName(userRequestUpdateDto.firstName())
      .lastName(userRequestUpdateDto.lastName())
      .email(userRequestUpdateDto.email())
      .password(userRequestUpdateDto.password())
      .updatedAt(LocalDateTime.now())
      .build();
  }

}
