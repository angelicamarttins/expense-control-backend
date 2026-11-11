package com.angelicamartins.expensecontrol.model.dto;

import jakarta.validation.constraints.Email;

public record ForgotPasswordRequestDto(@Email String userEmail) {}
