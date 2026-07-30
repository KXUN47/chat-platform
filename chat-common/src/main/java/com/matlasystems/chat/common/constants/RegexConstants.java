package com.matlasystems.chat.common.constants;

/**
 * Regular expressions used to validate common user and protocol values.
 */
public final class RegexConstants {

    private RegexConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String USERNAME = "^[A-Za-z0-9_.-]{3,32}$";
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,128}$";
    public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$";
    public static final String UUID =
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
    public static final String SAFE_FILE_NAME = "^[^\\\\/:*?\"<>|\\p{Cntrl}]+$";
    public static final String FILE_EXTENSION = "^\\.[A-Za-z0-9]{1,15}$";
    public static final String IPV4_ADDRESS =
            "^(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(?:\\.(?:25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}$";
}
