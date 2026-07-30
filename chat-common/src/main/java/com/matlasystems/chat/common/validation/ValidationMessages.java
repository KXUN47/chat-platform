package com.matlasystems.chat.common.validation;

public final class ValidationMessages {

    public static final String USERNAME_REQUIRED =
            "Username is required";

    public static final String USERNAME_LENGTH =
            "Username must be between 3 and 32 characters";

    public static final String USERNAME_FORMAT =
            "Username contains unsupported characters";

    private ValidationMessages() {
        throw new UnsupportedOperationException("Utility class");
    }
}
