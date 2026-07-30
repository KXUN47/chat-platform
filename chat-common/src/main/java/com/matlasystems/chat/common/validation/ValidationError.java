package com.matlasystems.chat.common.validation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/** A single validation failure associated with a field. */
public final class ValidationError implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private final String field;
    private final String code;
    private final String message;
    public ValidationError(String field, String code, String message) {
        this.field = field;
        this.code = code;
        this.message = message;
    }
    public String getField() { return field; }
    public String getCode() { return code; }
    public String getMessage() { return message; }
    @Override public boolean equals(Object object) {
        return this == object || (object instanceof ValidationError other && Objects.equals(field, other.field)
                && Objects.equals(code, other.code) && Objects.equals(message, other.message));
    }
    @Override public int hashCode() { return Objects.hash(field, code, message); }
    @Override public String toString() { return "ValidationError{" + "field='" + field + '\'' + ", code='" + code + '\'' + ", message='" + message + '\'' + '}'; }
}
