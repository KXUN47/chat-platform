package com.matlasystems.chat.common.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for {@link Request}.
 */
class RequestTest {

    @Test
    void createsRequestPackets() {

        Request request =
                new Request(
                        CommandType.LOGIN,
                        "payload");

        assertTrue(
                request.isRequest());

        assertEquals(
                CommandType.LOGIN,
                request.getHeader().getCommand());

        assertEquals(
                "payload",
                request.getPayload());
    }

}
