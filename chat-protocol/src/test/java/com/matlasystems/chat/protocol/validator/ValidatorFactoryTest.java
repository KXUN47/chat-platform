package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link ValidatorFactory}.
 */
class ValidatorFactoryTest {

    @Test
    void returnsTheSameSharedInstances() {

        assertSame(ValidatorFactory.commandValidator(), ValidatorFactory.commandValidator());
        assertSame(ValidatorFactory.versionValidator(), ValidatorFactory.versionValidator());
        assertSame(ValidatorFactory.headerValidator(), ValidatorFactory.headerValidator());
        assertSame(ValidatorFactory.payloadValidator(), ValidatorFactory.payloadValidator());
        assertSame(ValidatorFactory.packetValidator(), ValidatorFactory.packetValidator());
    }

    @Test
    void packetValidatorValidatesAFullyValidPacket() {

        Packet packet = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), "hello");

        assertTrue(ValidatorFactory.packetValidator().validate(packet).isValid());
    }

}
