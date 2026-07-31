package com.matlasystems.chat.protocol.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Aggregates all validation failures found for a protocol value. */
public final class ValidationResult {

    private final List<ValidationError> errors = new ArrayList<>();

    private ValidationResult() {
    }

    public static ValidationResult valid() {
        return new ValidationResult();
    }

    public static ValidationResult invalid(String field, String code, String message) {
        return valid().addError(field, code, message);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public boolean isInvalid() {
        return !isValid();
    }

    public List<ValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public ValidationResult addError(String field, String code, String message) {
        return addError(new ValidationError(field, code, message));
    }

    public ValidationResult addError(ValidationError error) {
        if (error != null) {
            errors.add(error);
        }
        return this;
    }

    public ValidationResult merge(ValidationResult other) {
        if (other != null) {
            other.errors.forEach(this::addError);
        }
        return this;
    }

    public void throwIfInvalid() {
        if (isInvalid()) {
            throw new ValidationException("Protocol validation failed: " + errors);
        }
    }
}
