package com.angelicamartins.expensecontrol.repository.auth;

import com.angelicamartins.expensecontrol.model.auth.Otp;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, UUID> {
}
