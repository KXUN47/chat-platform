package com.matlasystems.chat.common.constants;

/**
 * Security related application constants.
 *
 * Centralizes authentication, password,
 * session and authorization settings.
 */
public final class SecurityConstants {

    private SecurityConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
     * Password Policy
     */
    public static final int MIN_PASSWORD_LENGTH = 8;

    public static final int MAX_PASSWORD_LENGTH = 128;

    public static final int MIN_USERNAME_LENGTH = 3;

    public static final int MAX_USERNAME_LENGTH = 32;

    /*
     * Password Hashing
     *
     * Change these if switching algorithms.
     */
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";

    public static final int PBKDF2_ITERATIONS = 65_536;

    public static final int HASH_LENGTH = 256;

    public static final int SALT_LENGTH = 16;

    /*
     * Session
     */
    public static final int SESSION_TIMEOUT_MINUTES = 30;

    public static final int SESSION_CLEANUP_INTERVAL_MINUTES = 5;

    /*
     * Login Protection
     */
    public static final int MAX_LOGIN_ATTEMPTS = 5;

    public static final int ACCOUNT_LOCK_DURATION_MINUTES = 15;

    /*
     * Token
     */
    public static final int SESSION_TOKEN_LENGTH = 64;

    /*
     * Authentication Headers
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER_PREFIX = "Bearer ";

    /*
     * Encryption
     */
    public static final String AES_ALGORITHM = "AES";

    public static final int AES_KEY_SIZE = 256;

    /*
     * Secure Random
     */
    public static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";

    /*
     * File Upload Security
     */
    public static final boolean VIRUS_SCAN_ENABLED = false;

    /*
     * Security Logging
     */
    public static final boolean AUDIT_LOG_ENABLED = true;

}
