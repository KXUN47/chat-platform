package com.matlasystems.chat.protocol.validator;

import com.matlasystems.chat.common.enums.CommandType;

/** Validates a protocol command value. */
public final class CommandValidator implements Validator<CommandType> {

    @Override
    public ValidationResult validate(CommandType command) {
        return command == null
                ? ValidationResult.invalid("command", "required", "Packet command is required")
                : ValidationResult.valid();
    }
}
