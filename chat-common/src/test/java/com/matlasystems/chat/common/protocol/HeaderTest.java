package com.matlasystems.chat.common.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for {@link Header}.
 */
class HeaderTest {

    @Test
    void createsHeaderMetadata() {

        Header header = Header.create(
                CommandType.LOGIN,
                PacketStatus.REQUEST);

        assertNotNull(header.getPacketId());
        assertNotNull(header.getTimestamp());

        assertEquals(
                ProtocolVersion.V1_0,
                header.getProtocolVersion());

        assertEquals(
                CommandType.LOGIN,
                header.getCommand());
    }

}
