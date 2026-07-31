package com.matlasystems.chat.protocol.util;

import java.io.Serializable;
import java.time.Instant;

import com.matlasystems.chat.common.dto.response.SuccessResponse;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.Response;

/**
 * Creates standard success {@link Response} packets.
 */
public final class ResponseFactory {

    private ResponseFactory() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Creates a success response carrying a {@link SuccessResponse} message,
     * correlated with the given request header.
     */
    public static Response success(Header requestHeader, String message) {

        Header responseHeader = HeaderUtils.deriveResponseHeader(requestHeader, PacketStatus.RESPONSE);

        SuccessResponse payload = new SuccessResponse(
                message,
                requestHeader.getPacketId() == null ? null : requestHeader.getPacketId().toString(),
                Instant.now());

        return new Response(responseHeader, payload);

    }

    /**
     * Creates a success response with a custom payload, correlated with the
     * given request header.
     */
    public static Response withPayload(Header requestHeader, Serializable payload) {

        return new Response(
                HeaderUtils.deriveResponseHeader(requestHeader, PacketStatus.RESPONSE),
                payload);

    }

}
