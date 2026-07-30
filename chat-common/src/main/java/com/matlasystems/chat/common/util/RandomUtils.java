package com.matlasystems.chat.common.util;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.Base64;

/**
 * Utility class for generating secure random values.
 */
public final class RandomUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    private static final String UPPER =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String LOWER =
            "abcdefghijklmnopqrstuvwxyz";

    private static final String DIGITS =
            "0123456789";

    private static final String SYMBOLS =
            "!@#$%^&*()-_=+[]{}<>?";

    private static final String ALPHABET =
            UPPER + LOWER;

    private static final String ALPHANUMERIC =
            ALPHABET + DIGITS;

    private static final String HEX =
            "0123456789ABCDEF";

    private RandomUtils() {
        throw new UnsupportedOperationException(
                "Utility class");
    }

    /**
     * Random integer.
     */
    public static int nextInt(int bound) {

        if (bound <= 0) {
            throw new IllegalArgumentException(
                    "Bound must be greater than zero.");
        }

        return RANDOM.nextInt(bound);
    }

    /**
     * Random integer in range.
     */
    public static int nextInt(
            int min,
            int max) {

        if (min >= max) {
            throw new IllegalArgumentException(
                    "Minimum must be less than maximum.");
        }

        return RANDOM.nextInt(max - min) + min;
    }

    /**
     * Random long.
     */
    public static long nextLong() {
        return RANDOM.nextLong();
    }

    /**
     * Random double.
     */
    public static double nextDouble() {
        return RANDOM.nextDouble();
    }

    /**
     * Random boolean.
     */
    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * Random byte array.
     */
    public static byte[] nextBytes(
            int length) {

        if (length <= 0) {
            throw new IllegalArgumentException(
                    "Length must be positive.");
        }

        byte[] bytes = new byte[length];
        RANDOM.nextBytes(bytes);

        return bytes;
    }

    /**
     * UUID.
     */
    public static UUID uuid() {
        return UUID.randomUUID();
    }

    /**
     * Numeric string.
     */
    public static String numeric(
            int length) {

        return generate(length, DIGITS);
    }

    /**
     * Alphabetic string.
     */
    public static String alphabetic(
            int length) {

        return generate(length, ALPHABET);
    }

    /**
     * Alphanumeric string.
     */
    public static String alphanumeric(
            int length) {

        return generate(length, ALPHANUMERIC);
    }

    /**
     * Hexadecimal string.
     */
    public static String hex(
            int length) {

        return generate(length, HEX);
    }

    /**
     * Generates secure password.
     */
    public static String password(
            int length) {

        if (length < 8) {
            throw new IllegalArgumentException(
                    "Password length must be at least 8.");
        }

        List<Character> chars = new ArrayList<>();

        chars.add(randomChar(UPPER));
        chars.add(randomChar(LOWER));
        chars.add(randomChar(DIGITS));
        chars.add(randomChar(SYMBOLS));

        String all =
                UPPER +
                LOWER +
                DIGITS +
                SYMBOLS;

        while (chars.size() < length) {

            chars.add(randomChar(all));
        }

        Collections.shuffle(chars, RANDOM);

        StringBuilder builder =
                new StringBuilder(length);

        for (char c : chars) {
            builder.append(c);
        }

        return builder.toString();
    }

    /**
     * URL-safe token.
     */
    public static String token(
            int bytes) {

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(
                        nextBytes(bytes));
    }

    /**
     * Random element from list.
     */
    public static <T> T randomFrom(
            List<T> values) {

        Objects.requireNonNull(values);

        if (values.isEmpty()) {

            throw new IllegalArgumentException(
                    "Collection cannot be empty.");
        }

        return values.get(
                nextInt(values.size()));
    }

    /**
     * Shuffle list.
     */
    public static <T> void shuffle(
            List<T> values) {

        Objects.requireNonNull(values);

        Collections.shuffle(values, RANDOM);
    }

    /**
     * Generates random string
     * using supplied characters.
     */
    private static String generate(
            int length,
            String characters) {

        if (length <= 0) {

            throw new IllegalArgumentException(
                    "Length must be positive.");
        }

        StringBuilder builder =
                new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            builder.append(
                    randomChar(characters));
        }

        return builder.toString();
    }

    /**
     * Picks random character.
     */
    private static char randomChar(
            String source) {

        return source.charAt(
                RANDOM.nextInt(source.length()));
    }

}
