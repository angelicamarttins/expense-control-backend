package com.angelicamartins.expensecontrol.security.otp;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HexFormat;
import org.springframework.stereotype.Component;

@Component
public class OtpHasher {

  private static final int SALT_LENGTH = 16;
  private static final SecureRandom RANDOM = new SecureRandom();

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

  public String hashToken(String rawToken) {
    return HexFormat.of().formatHex(sha256(new byte[0], rawToken));
  }

  public boolean matchesToken(String rawToken, String storedHash) {
    byte[] expected = HexFormat.of().parseHex(storedHash);
    byte[] actual = sha256(new byte[0], rawToken);
    return MessageDigest.isEqual(expected, actual);
  }

  private byte[] sha256(byte[] salt, String rawOtp) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      digest.update(salt);
      return digest.digest(rawOtp.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
  }

}
