package com.matlasystems.chat.protocol.util;

import java.util.Optional;

import com.matlasystems.chat.common.protocol.Packet;

/**
 * General-purpose helper methods for working with {@link Packet} instances.
 */
public final class PacketUtils {

    private PacketUtils() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Determines whether a response packet correlates with a request packet.
     */
    public static boolean isResponseTo(Packet response, Packet request) {

        if (response == null || request == null) {
            return false;
        }

        return HeaderUtils.correlates(response.getHeader(), request.getHeader());

    }

    /**
     * Casts a packet's payload to the requested type, or returns empty if
     * the payload is absent or does not match the requested type.
     */
    public static <T> Optional<T> payloadAs(Packet packet, Class<T> type) {

        if (packet == null || packet.getPayload() == null) {
            return Optional.empty();
        }

        Object payload = packet.getPayload();

        return type.isInstance(payload)
                ? Optional.of(type.cast(payload))
                : Optional.empty();

    }

    /**
     * Determines whether a packet carries no payload.
     */
    public static boolean hasNoPayload(Packet packet) {

        return packet == null || packet.getPayload() == null;

    }

}
