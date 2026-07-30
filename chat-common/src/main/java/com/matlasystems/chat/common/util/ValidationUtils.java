package com.matlasystems.chat.common.util;

import java.util.regex.Pattern;

import com.matlasystems.chat.common.constants.RegexConstants;
import com.matlasystems.chat.common.constants.ValidationConstants;

/** Stateless primitive validation helpers shared by the validator package. */
public final class ValidationUtils {
    private static final Pattern USERNAME = Pattern.compile(RegexConstants.USERNAME);
    private static final Pattern PASSWORD = Pattern.compile(RegexConstants.PASSWORD);
    private static final Pattern EMAIL = Pattern.compile(RegexConstants.EMAIL);
    private static final Pattern UUID = Pattern.compile(RegexConstants.UUID);
    private static final Pattern SAFE_FILE_NAME = Pattern.compile(RegexConstants.SAFE_FILE_NAME);
    private static final Pattern IPV4 = Pattern.compile(RegexConstants.IPV4_ADDRESS);
    private ValidationUtils() { throw new UnsupportedOperationException("Utility class"); }
    public static boolean hasLengthBetween(String value, int min, int max) {
        return value != null && value.length() >= min && value.length() <= max;
    }
    public static boolean isValidUsername(String value) { return value != null && USERNAME.matcher(value).matches(); }
    public static boolean isValidPassword(String value) { return value != null && PASSWORD.matcher(value).matches(); }
    public static boolean isValidEmail(String value) { return value != null && EMAIL.matcher(value).matches(); }
    public static boolean isValidUuid(String value) { return value != null && UUID.matcher(value).matches(); }
    public static boolean isValidFileName(String value) {
        return hasLengthBetween(value, 1, ValidationConstants.MAX_FILE_NAME_LENGTH)
                && SAFE_FILE_NAME.matcher(value).matches() && !value.equals(".") && !value.equals("..");
    }
    public static boolean isValidPort(Integer value) {
        return value != null && value >= ValidationConstants.MIN_PORT && value <= ValidationConstants.MAX_PORT;
    }
    public static boolean isValidIpv4Address(String value) { return value != null && IPV4.matcher(value).matches(); }
    public static boolean isValidContentType(String value) {
        return hasLengthBetween(value, 1, ValidationConstants.MAX_CONTENT_TYPE_LENGTH) && value.contains("/");
    }
}
