package com.matlasystems.chat.protocol.version;

import java.time.Instant;
import java.util.Objects;

/**
 * Descriptive metadata attached to a supported {@link ProtocolVersion}.
 */
public final class VersionMetadata {

    private final ProtocolVersion version;

    private final String description;

    private final boolean deprecated;

    private final Instant releasedAt;

    private VersionMetadata(
            ProtocolVersion version,
            String description,
            boolean deprecated,
            Instant releasedAt) {

        this.version = Objects.requireNonNull(version, "version must not be null");
        this.description = description;
        this.deprecated = deprecated;
        this.releasedAt = releasedAt;

    }

    /**
     * Creates metadata for a supported version.
     */
    public static VersionMetadata of(
            ProtocolVersion version,
            String description,
            boolean deprecated,
            Instant releasedAt) {

        return new VersionMetadata(version, description, deprecated, releasedAt);

    }

    /**
     * Creates metadata for a supported, non-deprecated version released now.
     */
    public static VersionMetadata of(ProtocolVersion version, String description) {

        return new VersionMetadata(version, description, false, Instant.now());

    }

    public ProtocolVersion getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public Instant getReleasedAt() {
        return releasedAt;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VersionMetadata other)) {
            return false;
        }

        return version.equals(other.version);

    }

    @Override
    public int hashCode() {

        return version.hashCode();

    }

    @Override
    public String toString() {

        return "VersionMetadata{version=" + version + ", deprecated=" + deprecated + "}";

    }

}
