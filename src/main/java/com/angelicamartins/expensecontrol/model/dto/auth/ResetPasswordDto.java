package com.angelicamartins.expensecontrol.model.dto.auth;

public record ResetPasswordDto() {
  public record Request(String userEmail, String newPassword) {
  }
}
