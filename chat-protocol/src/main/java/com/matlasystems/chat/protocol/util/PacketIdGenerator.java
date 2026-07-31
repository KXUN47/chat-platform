package com.matlasystems.chat.protocol.util;

import java.util.UUID;

/**
 * Generates and parses unique identifiers for protocol packets.
 */
public final class PacketIdGenerator {

    private PacketIdGenerator() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Generates a new random packet identifier.
     */
    public static UUID generate() {

        return UUID.randomUUID();

    }

    /**
     * Parses a packet identifier from its string form.
     */
    public static UUID parse(String value) {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Packet id cannot be blank.");
        }

        return UUID.fromString(value);

    }

}
