package com.matlasystems.chat.protocol.version;

/**
 * Determines whether protocol versions can interoperate.
 *
 * Follows semantic-versioning rules: versions are considered compatible
 * when they share the same major component, since a change in major
 * version signals a breaking change to the wire format.
 */
public final class VersionCompatibility {

    private VersionCompatibility() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Determines whether two versions are compatible with each other.
     */
    public static boolean areCompatible(ProtocolVersion first, ProtocolVersion second) {

        if (first == null || second == null) {
            return false;
        }

        return first.getMajor() == second.getMajor();

    }

    /**
     * Determines whether a server running {@code server} can serve a
     * client running {@code client}.
     *
     * A server supports a client of the same major version whose minor
     * version does not exceed its own, since minor versions only add
     * backward-compatible functionality.
     */
    public static boolean serverSupportsClient(ProtocolVersion server, ProtocolVersion client) {

        if (server == null || client == null) {
            return false;
        }

        return server.getMajor() == client.getMajor()
                && server.getMinor() >= client.getMinor();

    }

}
