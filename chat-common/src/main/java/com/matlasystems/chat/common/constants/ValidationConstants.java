package com.matlasystems.chat.common.constants;

/**
 * Input validation limits shared by clients and server components.
 */
public final class ValidationConstants {

    private ValidationConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MAX_USERNAME_LENGTH = 32;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_PASSWORD_LENGTH = 128;
    public static final int MAX_DISPLAY_NAME_LENGTH = 64;

    public static final int MAX_MESSAGE_LENGTH = 5_000;
    public static final int MAX_PACKET_ID_LENGTH = 64;
    public static final int MAX_COMMAND_LENGTH = 64;
    public static final int MAX_FILE_NAME_LENGTH = 255;
    public static final int MAX_FILE_EXTENSION_LENGTH = 16;
    public static final int MAX_CONTENT_TYPE_LENGTH = 128;

    public static final int MIN_PORT = 1;
    public static final int MAX_PORT = 65_535;
    public static final long MAX_FILE_SIZE_BYTES = 100L * 1024L * 1024L;
    public static final int MAX_PACKET_SIZE_BYTES = 10 * 1024 * 1024;
}
