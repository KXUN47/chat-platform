package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.util.StringUtils;

/** Validates email address syntax. */
public final class EmailValidator implements Validator<String> {
    @Override public ValidationResult validate(String email) {
        if (StringUtils.isBlank(email)) { return ValidationResult.invalid("email", "required", "Email address is required"); }
        return com.matlasystems.chat.common.util.ValidationUtils.isValidEmail(email)
                ? ValidationResult.valid() : ValidationResult.invalid("email", "format", "Email address is invalid");
    }
}
