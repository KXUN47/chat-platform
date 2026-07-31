package com.matlasystems.chat.protocol.util;

import java.time.Instant;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.common.protocol.ProtocolVersion;

/**
 * Helper methods for creating and deriving protocol headers.
 */
public final class HeaderUtils {

    private HeaderUtils() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Creates a new header stamped with the current time and protocol version.
     */
    public static Header create(CommandType command, PacketStatus status) {

        return Header.create(command, status);

    }

    /**
     * Derives a response header from a request header, preserving the
     * packet id so the response can be correlated with its request.
     */
    public static Header deriveResponseHeader(Header requestHeader, PacketStatus status) {

        if (requestHeader == null) {
            throw new IllegalArgumentException("Request header cannot be null.");
        }

        return new Header(
                requestHeader.getPacketId(),
                Instant.now(),
                requestHeader.getProtocolVersion() == null
                        ? ProtocolVersion.current()
                        : requestHeader.getProtocolVersion(),
                requestHeader.getCommand(),
                status);

    }

    /**
     * Determines whether a header is fully populated with every required field.
     */
    public static boolean isComplete(Header header) {

        return header != null
                && header.getPacketId() != null
                && header.getTimestamp() != null
                && header.getProtocolVersion() != null
                && header.getCommand() != null
                && header.getStatus() != null;

    }

    /**
     * Determines whether two headers share the same packet id.
     */
    public static boolean correlates(Header first, Header second) {

        return first != null
                && second != null
                && first.getPacketId() != null
                && first.getPacketId().equals(second.getPacketId());

    }

}
