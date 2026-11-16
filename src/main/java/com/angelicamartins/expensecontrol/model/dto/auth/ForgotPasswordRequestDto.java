package com.angelicamartins.expensecontrol.model.dto.auth;

import jakarta.validation.constraints.Email;

public record ForgotPasswordRequestDto(@Email String userEmail) {}
