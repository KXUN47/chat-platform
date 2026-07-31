package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for the validator package's {@link PacketValidator}.
 */
class PacketValidatorTest {

    private final PacketValidator validator = new PacketValidator();

    @Test
    void rejectsANullPacket() {

        assertTrue(validator.validate(null).isInvalid());
    }

    @Test
    void acceptsAFullyValidPacket() {

        Packet packet = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), "hello");

        assertTrue(validator.validate(packet).isValid());
    }

    @Test
    void mergesHeaderErrorsWhenHeaderIsMissing() {

        Packet packet = new Packet(null, "valid payload");

        ValidationResult result = validator.validate(packet);

        assertTrue(result.isInvalid());
        assertTrue(result.getErrors().stream().anyMatch(error -> error.getField().equals("header")));
    }

}
