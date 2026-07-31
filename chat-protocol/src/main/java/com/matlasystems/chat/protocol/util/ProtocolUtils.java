package com.matlasystems.chat.protocol.util;

import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.ProtocolVersion;

/**
 * General protocol-level helper checks that do not belong to a single component.
 */
public final class ProtocolUtils {

    private ProtocolUtils() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Determines whether a packet is a request expecting a response.
     */
    public static boolean expectsResponse(Packet packet) {

        return packet != null && packet.isRequest();

    }

    /**
     * Determines whether a packet was sent using the current protocol version.
     */
    public static boolean isCurrentVersion(Packet packet) {

        return packet != null
                && packet.getHeader() != null
                && ProtocolVersion.current().equals(packet.getHeader().getProtocolVersion());

    }

    /**
     * Determines whether a packet represents a terminal outcome, i.e. a
     * response or an error, as opposed to a request or event.
     */
    public static boolean isTerminal(Packet packet) {

        return packet != null
                && packet.getHeader() != null
                && (packet.getHeader().getStatus() == PacketStatus.RESPONSE
                        || packet.getHeader().getStatus() == PacketStatus.ERROR);

    }

}
