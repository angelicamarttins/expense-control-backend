package com.angelicamartins.expensecontrol.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
  @NotBlank
  String firstName,
  @NotBlank
  String lastName,
  @NotBlank
  String email,
  @NotBlank
  String password
) {}
