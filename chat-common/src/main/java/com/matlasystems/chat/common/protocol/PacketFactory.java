package com.matlasystems.chat.common.protocol;

import java.io.Serializable;
import java.time.Instant;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Factory methods for creating protocol packets with valid standard headers.
 */
public final class PacketFactory {

    private PacketFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Request request(CommandType command) {
        return request(command, null);
    }

    public static Request request(CommandType command, Serializable payload) {
        return new Request(command, payload);
    }

    public static Response response(Packet request, Serializable payload) {
        return responseFor(request, PacketStatus.RESPONSE, payload);
    }

    public static Response error(Packet request, Serializable payload) {
        return responseFor(request, PacketStatus.ERROR, payload);
    }

    public static Packet event(CommandType command, Serializable payload) {
        return new Packet(Header.create(command, PacketStatus.EVENT), payload);
    }

    private static Response responseFor(
            Packet request,
            PacketStatus status,
            Serializable payload) {

        if (request == null || request.getHeader() == null) {
            throw new IllegalArgumentException("A request with a header is required");
        }

        Header requestHeader = request.getHeader();

        Header responseHeader = new Header(
                requestHeader.getPacketId(),
                Instant.now(),
                requestHeader.getProtocolVersion() == null
                        ? ProtocolVersion.current()
                        : requestHeader.getProtocolVersion(),
                requestHeader.getCommand(),
                status);

        return new Response(responseHeader, payload);
    }
}
