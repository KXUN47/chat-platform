package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.dto.response.ErrorResponse;
import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.enums.ErrorCode;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.Response;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link ErrorPacketFactory}.
 */
class ErrorPacketFactoryTest {

    @Test
    void buildsAnErrorResponseFromAnErrorCode() {

        Header request = Header.create(CommandType.LOGIN, PacketStatus.REQUEST);

        Response response = ErrorPacketFactory.of(request, ErrorCode.AUTHENTICATION_FAILED);

        assertTrue(response.isError());
        assertEquals(request.getPacketId(), response.getHeader().getPacketId());

        ErrorResponse payload = (ErrorResponse) response.getPayload();
        assertEquals(String.valueOf(ErrorCode.AUTHENTICATION_FAILED.getCode()), payload.getCode());
        assertEquals(ErrorCode.AUTHENTICATION_FAILED.getMessage(), payload.getMessage());
    }

    @Test
    void buildsAnErrorResponseWithACustomMessage() {

        Header request = Header.create(CommandType.LOGIN, PacketStatus.REQUEST);

        Response response = ErrorPacketFactory.of(request, ErrorCode.INVALID_REQUEST, "custom message");

        ErrorResponse payload = (ErrorResponse) response.getPayload();
        assertEquals("custom message", payload.getMessage());
    }

    @Test
    void buildsAnErrorResponseFromAProtocolException() {

        Header request = Header.create(CommandType.LOGIN, PacketStatus.REQUEST);
        ProtocolException exception = new ProtocolException("boom");

        Response response = ErrorPacketFactory.of(request, exception);

        ErrorResponse payload = (ErrorResponse) response.getPayload();
        assertEquals("boom", payload.getMessage());
    }

}
