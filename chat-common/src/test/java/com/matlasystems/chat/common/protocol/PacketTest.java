package com.matlasystems.chat.common.protocol;
import static org.junit.jupiter.api.Assertions.*;
import com.matlasystems.chat.common.enums.CommandType;
import org.junit.jupiter.api.Test;

class PacketTest {

    @Test void identifiesPacketStatus() {

        Packet packet=new Packet(Header.create(CommandType.PING,PacketStatus.EVENT),"payload");
        assertTrue(packet.isEvent());
        assertFalse(packet.isRequest());
        assertEquals("payload",packet.getPayload());
    }
}
