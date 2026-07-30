package com.matlasystems.chat.common.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/** Result of applying one or more validators. */
public final class ValidationResult {
    private final List<ValidationError> errors = new ArrayList<>();
    private ValidationResult() { }
    public static ValidationResult valid() { return new ValidationResult(); }
    public static ValidationResult invalid(String field, String code, String message) {
        return valid().addError(field, code, message);
    }
    public boolean isValid() { return errors.isEmpty(); }
    public boolean isInvalid() { return !isValid(); }
    public List<ValidationError> getErrors() { return Collections.unmodifiableList(errors); }
    public ValidationResult addError(String field, String code, String message) {
        errors.add(new ValidationError(field, code, message));
        return this;
    }
    public ValidationResult addError(ValidationError error) {
        if (error != null) { errors.add(error); }
        return this;
    }
    public ValidationResult addErrors(Collection<ValidationError> validationErrors) {
        if (validationErrors != null) { validationErrors.forEach(this::addError); }
        return this;
    }
    public ValidationResult merge(ValidationResult other) {
        return other == null ? this : addErrors(other.errors);
    }
}
