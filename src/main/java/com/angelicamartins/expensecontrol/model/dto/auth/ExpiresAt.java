package com.angelicamartins.expensecontrol.model.dto.auth;

import java.time.LocalDateTime;

public record ExpiresAt(Long expiresAt, LocalDateTime createdAt) {
}
