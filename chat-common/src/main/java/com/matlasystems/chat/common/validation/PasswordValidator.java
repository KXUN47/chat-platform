package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.util.StringUtils;

/** Validates the configured password complexity policy. */
public final class PasswordValidator implements Validator<String> {
    @Override public ValidationResult validate(String password) {
        if (StringUtils.isBlank(password)) { return ValidationResult.invalid("password", "required", "Password is required"); }
        return com.matlasystems.chat.common.util.ValidationUtils.isValidPassword(password)
                ? ValidationResult.valid()
                : ValidationResult.invalid("password", "format", "Password must contain upper-case, lower-case, and numeric characters and be 8 to 128 characters long");
    }
}
