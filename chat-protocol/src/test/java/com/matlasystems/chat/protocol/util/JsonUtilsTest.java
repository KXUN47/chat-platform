package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for the util package's {@link JsonUtils}.
 */
class JsonUtilsTest {

    @Test
    void roundTripsAPacketThroughJson() {

        Packet original = new Packet(Header.create(CommandType.PING, PacketStatus.REQUEST), "hello");

        String json = JsonUtils.toJson(original);
        Packet parsed = JsonUtils.packetFromJson(json);

        assertEquals(original.getHeader().getPacketId(), parsed.getHeader().getPacketId());
    }

    @Test
    void roundTripsAPayloadThroughJson() {

        String json = JsonUtils.payloadToJson("hello");

        assertEquals("hello", JsonUtils.payloadFromJson(json, String.class));
    }

}
