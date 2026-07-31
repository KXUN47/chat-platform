package com.matlasystems.chat.protocol.validator;

import java.util.Objects;

/** Describes one protocol validation failure. */
public final class ValidationError {

    private final String field;
    private final String code;
    private final String message;

    public ValidationError(String field, String code, String message) {
        this.field = Objects.requireNonNull(field, "field must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.message = Objects.requireNonNull(message, "message must not be null");
    }

    public String getField() {
        return field;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ValidationError other)) {
            return false;
        }
        return field.equals(other.field)
                && code.equals(other.code)
                && message.equals(other.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, code, message);
    }
}
