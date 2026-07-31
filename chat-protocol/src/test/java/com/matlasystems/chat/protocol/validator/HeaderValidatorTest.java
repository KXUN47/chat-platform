package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link HeaderValidator}.
 */
class HeaderValidatorTest {

    private final HeaderValidator validator = new HeaderValidator();

    @Test
    void rejectsANullHeaderWithASingleError() {

        ValidationResult result = validator.validate(null);

        assertTrue(result.isInvalid());
        assertEquals(1, result.getErrors().size());
        assertEquals("header", result.getErrors().get(0).getField());
    }

    @Test
    void acceptsAFullyPopulatedHeader() {

        Header header = Header.create(CommandType.PING, PacketStatus.REQUEST);

        assertTrue(validator.validate(header).isValid());
    }

    @Test
    void accumulatesEveryMissingField() {

        Header header = new Header(null, null, null, null, null);

        ValidationResult result = validator.validate(header);

        assertTrue(result.isInvalid());
        assertTrue(result.getErrors().size() >= 5);
    }

    @Test
    void mergesCommandAndVersionValidatorErrors() {

        Header header = new Header(UUID.randomUUID(), Instant.now(), null, null, PacketStatus.REQUEST);

        ValidationResult result = validator.validate(header);

        boolean hasCommandError = result.getErrors().stream()
                .anyMatch(error -> error.getField().equals("command"));

        boolean hasVersionError = result.getErrors().stream()
                .anyMatch(error -> error.getField().equals("protocolVersion"));

        assertTrue(hasCommandError);
        assertTrue(hasVersionError);
    }

}
