package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.protocol.ProtocolVersion;

/**
 * Unit tests for {@link VersionValidator}.
 */
class VersionValidatorTest {

    private final VersionValidator validator = new VersionValidator();

    @Test
    void rejectsANullVersion() {

        assertTrue(validator.validate(null).isInvalid());
    }

    @Test
    void acceptsANonNullVersion() {

        assertTrue(validator.validate(ProtocolVersion.current()).isValid());
    }

}
