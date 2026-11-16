package com.angelicamartins.expensecontrol.controller.auth;

import com.angelicamartins.expensecontrol.model.dto.auth.ForgotPasswordRequestDto;
import com.angelicamartins.expensecontrol.model.dto.auth.ResetPasswordDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserDto;
import com.angelicamartins.expensecontrol.model.dto.user.UserRequestDto;
import com.angelicamartins.expensecontrol.service.auth.AuthService;
import com.angelicamartins.expensecontrol.service.auth.OtpService;
import com.angelicamartins.expensecontrol.service.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private final OtpService otpService;
  private final UserService userService;

  @GetMapping("/csrf")
  public ResponseEntity<CsrfToken> getCsrfToken(CsrfToken csrfToken) {
    return ResponseEntity.ok(csrfToken);
  }

  @PostMapping("/signup")
  public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto userRequestDto) {
    return ResponseEntity.ok(userService.saveUser(userRequestDto));
  }

  @PostMapping("forgot-password")
  public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto requestDto) {
    otpService.generateAndSendOtp(requestDto);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("/reset-password")
  public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordDto.Request resetPasswordRequestDto) {
    authService.resetPassword(resetPasswordRequestDto);

    return ResponseEntity.noContent().build();
  }

}
