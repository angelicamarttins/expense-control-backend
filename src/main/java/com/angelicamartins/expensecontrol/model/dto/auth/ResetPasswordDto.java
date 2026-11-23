package com.angelicamartins.expensecontrol.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record ResetPasswordDto() {
  public record Request(@Email String userEmail, @NotEmpty String newPassword, @NotEmpty String token) {
  }
}
