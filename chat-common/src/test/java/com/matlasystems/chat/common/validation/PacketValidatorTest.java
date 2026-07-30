package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketFactory;

/**
 * Unit tests for {@link PacketValidator}.
 */
class PacketValidatorTest {

    @Test
    void requiresPacketHeader() {

        PacketValidator validator =
                new PacketValidator();

        Packet packet =
                PacketFactory.request(
                        CommandType.PING);

        ValidationResult validResult =
                validator.validate(packet);

        ValidationResult invalidResult =
                validator.validate(null);

        assertTrue(
                validResult.isValid());

        assertTrue(
                invalidResult.isInvalid());
    }

}
