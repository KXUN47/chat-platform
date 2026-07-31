package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.dto.response.SuccessResponse;
import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.Response;

/**
 * Unit tests for {@link ResponseFactory}.
 */
class ResponseFactoryTest {

    @Test
    void successBuildsACorrelatedSuccessResponse() {

        Header request = Header.create(CommandType.LOGIN, PacketStatus.REQUEST);

        Response response = ResponseFactory.success(request, "welcome back");

        assertEquals(request.getPacketId(), response.getHeader().getPacketId());
        assertTrue(response.isSuccessful());

        SuccessResponse payload = (SuccessResponse) response.getPayload();
        assertEquals("welcome back", payload.getMessage());
        assertEquals(request.getPacketId().toString(), payload.getRequestId());
    }

    @Test
    void withPayloadCarriesACustomPayload() {

        Header request = Header.create(CommandType.LOGIN, PacketStatus.REQUEST);

        Response response = ResponseFactory.withPayload(request, "custom");

        assertEquals("custom", response.getPayload());
    }

}
