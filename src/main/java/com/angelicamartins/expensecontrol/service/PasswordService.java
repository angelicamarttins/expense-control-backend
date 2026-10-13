package com.angelicamartins.expensecontrol.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password4j.Argon2Password4jPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

  private static final PasswordEncoder passwordEncoder = new Argon2Password4jPasswordEncoder();

  public static String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }

}
