package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.common.util.StringUtils;

/** Validates message text before it is sent. */
public final class MessageValidator implements Validator<String> {
    @Override public ValidationResult validate(String message) {
        if (StringUtils.isBlank(message)) { return ValidationResult.invalid("message", "required", "Message cannot be empty"); }
        return message.length() <= ValidationConstants.MAX_MESSAGE_LENGTH
                ? ValidationResult.valid()
                : ValidationResult.invalid("message", "length", "Message exceeds the maximum allowed length");
    }
}
