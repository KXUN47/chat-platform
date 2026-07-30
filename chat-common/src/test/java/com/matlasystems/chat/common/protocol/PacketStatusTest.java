package com.matlasystems.chat.common.protocol;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PacketStatusTest {

    @Test void identifiesEachStatus() {

        assertTrue(PacketStatus.REQUEST.isRequest());
        assertTrue(PacketStatus.RESPONSE.isResponse());
        assertTrue(PacketStatus.EVENT.isEvent());
        assertTrue(PacketStatus.ERROR.isError());
    }
}
