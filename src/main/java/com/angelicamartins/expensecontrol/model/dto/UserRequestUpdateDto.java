package com.angelicamartins.expensecontrol.model.dto;

import com.angelicamartins.expensecontrol.model.validation.AtLeastOneField;

@AtLeastOneField
public record UserRequestUpdateDto(
  String firstName,
  String lastName,
  String email,
  String password
) {}
