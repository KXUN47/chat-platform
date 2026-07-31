package com.matlasystems.chat.protocol.version;

import java.util.Set;

/**
 * Negotiates a single protocol version acceptable to both sides of a
 * connection.
 */
public final class VersionNegotiator {

    /**
     * Selects the highest version present in both sets.
     *
     * @throws UnsupportedProtocolVersionException if no common version exists
     */
    public ProtocolVersion negotiate(
            Set<ProtocolVersion> clientVersions,
            Set<ProtocolVersion> serverVersions) {

        if (clientVersions == null || clientVersions.isEmpty()) {
            throw new IllegalArgumentException("Client versions cannot be empty.");
        }

        if (serverVersions == null || serverVersions.isEmpty()) {
            throw new IllegalArgumentException("Server versions cannot be empty.");
        }

        return clientVersions.stream()
                .filter(serverVersions::contains)
                .max(VersionComparator.ASCENDING)
                .orElseThrow(() ->
                        new UnsupportedProtocolVersionException(
                                clientVersions.stream()
                                        .max(VersionComparator.ASCENDING)
                                        .orElse(null)));

    }

    /**
     * Selects the highest server version compatible with a requested
     * client version, per {@link VersionCompatibility}.
     *
     * @throws UnsupportedProtocolVersionException if none are compatible
     */
    public ProtocolVersion negotiate(
            ProtocolVersion requested,
            Set<ProtocolVersion> serverVersions) {

        if (requested == null) {
            throw new IllegalArgumentException("Requested version cannot be null.");
        }

        if (serverVersions == null || serverVersions.isEmpty()) {
            throw new IllegalArgumentException("Server versions cannot be empty.");
        }

        return serverVersions.stream()
                .filter(server -> VersionCompatibility.serverSupportsClient(server, requested))
                .max(VersionComparator.ASCENDING)
                .orElseThrow(() -> new UnsupportedProtocolVersionException(requested));

    }

}
