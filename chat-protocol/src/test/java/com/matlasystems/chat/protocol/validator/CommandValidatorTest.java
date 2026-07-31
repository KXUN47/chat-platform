package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for the validator package's {@link CommandValidator}.
 */
class CommandValidatorTest {

    private final CommandValidator validator = new CommandValidator();

    @Test
    void rejectsANullCommand() {

        assertTrue(validator.validate(null).isInvalid());
    }

    @Test
    void acceptsAnyNonNullCommand() {

        assertTrue(validator.validate(CommandType.LOGIN).isValid());
    }

}
