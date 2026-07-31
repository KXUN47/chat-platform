package com.matlasystems.chat.protocol.util;

import java.time.Instant;

import com.matlasystems.chat.common.dto.response.ErrorResponse;
import com.matlasystems.chat.common.enums.ErrorCode;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.Response;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Creates standard error {@link Response} packets.
 */
public final class ErrorPacketFactory {

    private ErrorPacketFactory() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Creates an error response for a known error code, correlated with the request.
     */
    public static Response of(Header requestHeader, ErrorCode errorCode) {

        return of(requestHeader, errorCode, errorCode.getMessage());

    }

    /**
     * Creates an error response for a known error code with a custom message.
     */
    public static Response of(Header requestHeader, ErrorCode errorCode, String message) {

        Header responseHeader = HeaderUtils.deriveResponseHeader(requestHeader, PacketStatus.ERROR);

        ErrorResponse payload = new ErrorResponse(
                String.valueOf(errorCode.getCode()),
                message,
                requestHeader.getPacketId() == null ? null : requestHeader.getPacketId().toString(),
                Instant.now());

        return new Response(responseHeader, payload);

    }

    /**
     * Creates an error response from a caught protocol exception.
     */
    public static Response of(Header requestHeader, ProtocolException exception) {

        return of(requestHeader, exception.getErrorCode(), exception.getMessage());

    }

}
