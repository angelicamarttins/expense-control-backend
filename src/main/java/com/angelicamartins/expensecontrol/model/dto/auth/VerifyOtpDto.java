package com.angelicamartins.expensecontrol.model.dto.auth;

import jakarta.validation.constraints.NotEmpty;

public record VerifyOtpDto() {
  public record Request(@NotEmpty String rawOtp) {
  }
}
