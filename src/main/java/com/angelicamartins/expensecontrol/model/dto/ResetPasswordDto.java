package com.angelicamartins.expensecontrol.model.dto;

public record ResetPasswordDto() {
  public record Request(String userEmail, String newPassword) {
  }
}
