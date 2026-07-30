package com.matlasystems.chat.common.validation;

/**
 * Standard validation error codes.
 */
public final class ValidationCodes {

    public static final String REQUIRED = "required";

    public static final String INVALID = "invalid";

    public static final String RANGE = "range";

    public static final String LENGTH = "length";
    
    public static final String FORMAT = "format";

    public static final String TOO_SHORT = "too_short";

    public static final String TOO_LONG = "too_long";

    public static final String DUPLICATE = "duplicate";

    private ValidationCodes() {
        throw new UnsupportedOperationException(
                "Utility class");
    }

}
