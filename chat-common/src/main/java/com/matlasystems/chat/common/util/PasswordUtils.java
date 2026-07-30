package com.matlasystems.chat.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for password hashing,
 * verification and validation.
 *
 * Uses BCrypt for secure password hashing.
 */
public final class PasswordUtils {

    /**
     * BCrypt work factor.
     */
    private static final int BCRYPT_COST = 12;

    /**
     * Password length constraints.
     */
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 128;

    /**
     * Utility class.
     */
    private PasswordUtils() {
        throw new UnsupportedOperationException(
                "Utility class");
    }

    /**
     * Hashes a password using BCrypt.
     *
     * @param password raw password
     * @return BCrypt hash
     */
    public static String hash(String password) {

        validate(password);

        return BCrypt.hashpw(
                password,
                BCrypt.gensalt(BCRYPT_COST)
        );
    }

    /**
     * Verifies password against hash.
     *
     * @param password raw password
     * @param hash BCrypt hash
     * @return true if password matches
     */
    public static boolean verify(
            String password,
            String hash) {

        Objects.requireNonNull(password);
        Objects.requireNonNull(hash);

        return BCrypt.checkpw(
                password,
                hash);
    }

    /**
     * Generates BCrypt salt.
     *
     * @return salt
     */
    public static String generateSalt() {
        return BCrypt.gensalt(BCRYPT_COST);
    }

    /**
     * Checks whether password meets
     * minimum complexity.
     *
     * Rules:
     *
     * - minimum 8 characters
     * - uppercase
     * - lowercase
     * - digit
     * - special character
     *
     * @param password password
     * @return true if strong
     */
    public static boolean isStrong(String password) {

        if (password == null) {
            return false;
        }

        int length = password.length();

        if (length < MIN_PASSWORD_LENGTH
                || length > MAX_PASSWORD_LENGTH) {
            return false;
        }

        boolean hasUppercase = password.chars()
                .anyMatch(Character::isUpperCase);

        boolean hasLowercase = password.chars()
                .anyMatch(Character::isLowerCase);

        boolean hasDigit = password.chars()
                .anyMatch(Character::isDigit);

        boolean hasSpecial = password.chars()
                .anyMatch(ch -> !Character.isLetterOrDigit(ch));

        return hasUppercase
                && hasLowercase
                && hasDigit
                && hasSpecial;
    }

    /**
     * Validates password length.
     */
    public static boolean isValidLength(
            String password) {

        if (password == null) {
            return false;
        }

        return password.length()
                >= MIN_PASSWORD_LENGTH
                &&
                password.length()
                <= MAX_PASSWORD_LENGTH;
    }

    /**
     * Constant-time comparison.
     *
     * Protects against timing attacks.
     */
    public static boolean secureEquals(
            String first,
            String second) {

        if (first == null || second == null) {
            return false;
        }

        return MessageDigest.isEqual(
                first.getBytes(StandardCharsets.UTF_8),
                second.getBytes(StandardCharsets.UTF_8)
        );
    }

    /**
     * Generates temporary password.
     *
     * Length = 12
     */
    public static String generateTemporaryPassword() {

        return RandomUtils.password(12);
    }

    private static PasswordCharacteristics analyzePassword(String password) {

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {

            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }

            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }

            if (hasUppercase
                    && hasLowercase
                    && hasDigit
                    && hasSpecial) {
                break;
            }
        }

        return new PasswordCharacteristics(
                hasUppercase,
                hasLowercase,
                hasDigit,
                hasSpecial);
    }

    private record PasswordCharacteristics(
            boolean hasUppercase,
            boolean hasLowercase,
            boolean hasDigit,
            boolean hasSpecial) {
    }

    /**
     * Returns password strength score.
     *
     * Score:
     * 0-5
     */
    public static int estimateStrength(String password) {

        if (password == null) {
            return 0;
        }

        int score = 0;

        int length = password.length();

        if (length >= MIN_PASSWORD_LENGTH) {
            score++;
        }

        if (length >= 12) {
            score++;
        }

        PasswordCharacteristics characteristics =
                analyzePassword(password);

        if (characteristics.hasUppercase()) {
            score++;
        }

        if (characteristics.hasLowercase()) {
            score++;
        }

        if (characteristics.hasDigit()) {
            score++;
        }

        if (characteristics.hasSpecial()) {
            score++;
        }

        return Math.min(score, 5);
    }

    /**
     * Validates password.
     *
     * Throws IllegalArgumentException
     * if invalid.
     */
    public static void validate(
            String password) {

        Objects.requireNonNull(
                password,
                "Password cannot be null");

        if (!isStrong(password)) {

            throw new IllegalArgumentException(
                    """
                    Password must contain:
                    - Minimum 8 characters
                    - Uppercase letter
                    - Lowercase letter
                    - Digit
                    - Special character
                    """);
        }
    }
}
