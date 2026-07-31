package com.matlasystems.chat.protocol.version;

import java.util.Comparator;

/**
 * Common comparators for ordering {@link ProtocolVersion} instances.
 */
public final class VersionComparator {

    /**
     * Orders versions from oldest to newest.
     */
    public static final Comparator<ProtocolVersion> ASCENDING = ProtocolVersion::compareTo;

    /**
     * Orders versions from newest to oldest.
     */
    public static final Comparator<ProtocolVersion> DESCENDING = ASCENDING.reversed();

    /**
     * Orders versions by major component only, ignoring minor and patch.
     */
    public static final Comparator<ProtocolVersion> MAJOR_ONLY =
            Comparator.comparingInt(ProtocolVersion::getMajor);

    private VersionComparator() {

        throw new UnsupportedOperationException("Utility class");

    }

}
