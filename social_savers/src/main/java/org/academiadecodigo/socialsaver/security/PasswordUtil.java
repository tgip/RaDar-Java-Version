package org.academiadecodigo.socialsaver.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility for password hashing and verification.
 * Uses BCrypt via Spring Security Crypto (spring-security-crypto).
 */
public final class PasswordUtil {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private PasswordUtil() {
        // utility
    }

    public static String hash(String plain) {
        if (plain == null) {
            return null;
        }
        return ENCODER.encode(plain);
    }

    public static boolean matches(String plain, String hash) {
        if (plain == null || hash == null) {
            return false;
        }
        return ENCODER.matches(plain, hash);
    }
}
