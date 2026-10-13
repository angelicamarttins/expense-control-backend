package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.ResetPasswordDto;
import com.angelicamartins.expensecontrol.model.dto.UserDto;
import com.angelicamartins.expensecontrol.model.dto.UserRequestDto;
import com.angelicamartins.expensecontrol.service.AuthService;
import com.angelicamartins.expensecontrol.service.UserService;
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

  private final UserService userService;
  private final AuthService authService;

  @GetMapping("/csrf")
  public ResponseEntity<CsrfToken> getCsrfToken(CsrfToken csrfToken) {
    return ResponseEntity.ok(csrfToken);
  }

  @PostMapping("/signup")
  public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto userRequestDto) {
    return ResponseEntity.ok(userService.saveUser(userRequestDto));
  }

  @PatchMapping("/reset-password")
  public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordDto.Request resetPasswordRequestDto) {
    authService.resetPassword(resetPasswordRequestDto);

    return ResponseEntity.noContent().build();
  }

}
