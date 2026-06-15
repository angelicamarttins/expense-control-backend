package com.angelicamartins.expensecontrol.model.dto;

public record UserRequestUpdateDto(
  String firstName,
  String lastName,
  String email,
  String password
) {}
