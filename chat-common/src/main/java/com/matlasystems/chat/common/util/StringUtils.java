package com.matlasystems.chat.common.util;

/** Common null-safe string operations. */
public final class StringUtils {
    private StringUtils() { throw new UnsupportedOperationException("Utility class"); }
    public static boolean isBlank(String value) { return value == null || value.isBlank(); }
    public static boolean hasText(String value) { return !isBlank(value); }
    public static String trimToNull(String value) { return isBlank(value) ? null : value.trim(); }
    public static String trimToEmpty(String value) { return value == null ? "" : value.trim(); }
    public static String defaultIfBlank(String value, String defaultValue) { return isBlank(value) ? defaultValue : value; }
    public static String truncate(String value, int maxLength) {
        if (maxLength < 0) { throw new IllegalArgumentException("maxLength must not be negative"); }
        return value == null || value.length() <= maxLength ? value : value.substring(0, maxLength);
    }
    public static String capitalize(String value) {
        if (isBlank(value)) { return value; }
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }
    public static String decapitalize(String value) {
        if (isBlank(value)) { return value; }
        return Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }
    public static String normalizeWhitespace(String value) {
        return isBlank(value) ? "" : value.trim().replaceAll("\\s+", " ");
    }
    public static boolean equalsIgnoreCase(String first, String second) {
        return first == null ? second == null : first.equalsIgnoreCase(second);
    }
}
