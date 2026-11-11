package com.angelicamartins.expensecontrol.service;

import com.angelicamartins.expensecontrol.model.User;
import com.angelicamartins.expensecontrol.model.dto.ForgotPasswordRequestDto;
import com.angelicamartins.expensecontrol.validator.UserValidator;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OtpService {

  private static final int SALT_LENGTH = 16;
  private static final SecureRandom RANDOM = new SecureRandom();

  private final UserValidator userValidator;

  public void generateAndSendOtp(ForgotPasswordRequestDto requestDto) {
    User user = userValidator.validateAndReturnUserByEmail(requestDto.userEmail());

    Map<String, String> otp = generateSecureRandomOtp();
    String rawOtp = otp.get("rawOtp");
    String hashedOtp = otp.get("hashedOtp");

    System.out.println(requestDto.userEmail());
    System.out.println(rawOtp);
    System.out.println(hashedOtp);
  }

  private Map<String, String> generateSecureRandomOtp() {
    int code = RANDOM.nextInt(1_000_000);
    String rawOtp = String.format("%06d", code);

    String hashedOtp = hashOtp(rawOtp);

    return Map.of("rawOtp", rawOtp, "hashedOtp", hashedOtp);
  }

  public String hashOtp(String rawOtp) {
    byte[] salt = new byte[SALT_LENGTH];
    RANDOM.nextBytes(salt);

    byte[] hash = sha256(salt, rawOtp);

    byte[] combined = new byte[SALT_LENGTH + hash.length];
    System.arraycopy(salt, 0, combined, 0, SALT_LENGTH);
    System.arraycopy(hash, 0, combined, SALT_LENGTH, hash.length);

    return Base64.getEncoder().encodeToString(combined);
  }

  public boolean matchesOtp(String rawOtp, String storedOtp) {
    byte[] combined = Base64.getDecoder().decode(storedOtp);

    byte[] salt = Arrays.copyOfRange(combined, 0, SALT_LENGTH);
    byte[] expected = Arrays.copyOfRange(combined, SALT_LENGTH, combined.length);

    byte[] actual = sha256(salt, rawOtp);
    return MessageDigest.isEqual(expected, actual);
  }

  private byte[] sha256(byte[] salt, String rawOtp) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(salt);
      return digest.digest(rawOtp.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e); // SHA-256 sempre existe na JVM
    }
  }

}
