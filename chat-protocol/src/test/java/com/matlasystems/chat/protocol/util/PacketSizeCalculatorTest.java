package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link PacketSizeCalculator}.
 */
class PacketSizeCalculatorTest {

    @Test
    void computesAPositiveSizeForARealPacket() {

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        "hello");

        assertTrue(
                PacketSizeCalculator.sizeOf(
                        packet) > 0);

        assertTrue(
                PacketSizeCalculator.isWithinLimit(
                        packet));

        assertDoesNotThrow(
                () -> PacketSizeCalculator.requireWithinLimit(
                        packet));

    }

    @Test
    void sizeOfNullPacketIsZero() {

        assertEquals(
                0,
                PacketSizeCalculator.sizeOf(
                        null));

    }

    @Test
    void rejectsAPacketLargerThanTheMaximumSize() {

        String oversizedPayload =
                "a".repeat(
                        ValidationConstants.MAX_PACKET_SIZE_BYTES + 1);

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        oversizedPayload);

        ProtocolException exception =
                assertThrows(
                        ProtocolException.class,
                        () -> PacketSizeCalculator.requireWithinLimit(
                                packet));

        assertNotNull(
                exception);

    }

}
