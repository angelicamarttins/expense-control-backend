package com.angelicamartins.expensecontrol.controller;

import com.angelicamartins.expensecontrol.model.dto.UserDto;
import com.angelicamartins.expensecontrol.model.dto.UserRequestDto;
import com.angelicamartins.expensecontrol.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto userRequestDto) {
    return ResponseEntity.ok(userService.saveUser(userRequestDto));
  }

}
