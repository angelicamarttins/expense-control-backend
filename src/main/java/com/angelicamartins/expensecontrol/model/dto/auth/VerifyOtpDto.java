package com.angelicamartins.expensecontrol.model.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record VerifyOtpDto() {
  public record Request(
    @JsonProperty("otp")
    @NotEmpty
    String rawOtp,
    @Email String userEmail
  ) {
  }

  public record Response(String token) {
  }

}
