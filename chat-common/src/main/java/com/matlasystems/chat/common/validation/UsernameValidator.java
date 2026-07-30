package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.common.util.StringUtils;
import com.matlasystems.chat.common.util.ValidationUtils;

/**
 * Validates usernames accepted by the chat platform.
 */
public final class UsernameValidator
        implements Validator<String> {

    @Override
    public ValidationResult validate(String username) {

        if (StringUtils.isBlank(username)) {
            return ValidationResult.invalid(
                    ValidationFields.USERNAME,
                    ValidationCodes.REQUIRED,
                    ValidationMessages.USERNAME_REQUIRED);
        }

        if (!ValidationUtils.hasLengthBetween(
                username,
                ValidationConstants.MIN_USERNAME_LENGTH,
                ValidationConstants.MAX_USERNAME_LENGTH)) {

            return ValidationResult.invalid(
                    ValidationFields.USERNAME,
                    ValidationCodes.LENGTH,
                    ValidationMessages.USERNAME_LENGTH);
        }

        return ValidationUtils.isValidUsername(username)
                ? ValidationResult.valid()
                : ValidationResult.invalid(
                        ValidationFields.USERNAME,
                        ValidationCodes.FORMAT,
                        ValidationMessages.USERNAME_FORMAT);
    }

}
