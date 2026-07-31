package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link VersionCompatibility}.
 */
class VersionCompatibilityTest {

    @Test
    void versionsWithTheSameMajorAreCompatible() {

        assertTrue(VersionCompatibility.areCompatible(
                ProtocolVersion.of(1, 0), ProtocolVersion.of(1, 5)));
    }

    @Test
    void versionsWithDifferentMajorsAreNotCompatible() {

        assertFalse(VersionCompatibility.areCompatible(
                ProtocolVersion.of(1, 0), ProtocolVersion.of(2, 0)));
    }

    @Test
    void nullVersionsAreNeverCompatible() {

        assertFalse(VersionCompatibility.areCompatible(null, ProtocolVersion.of(1, 0)));
        assertFalse(VersionCompatibility.areCompatible(ProtocolVersion.of(1, 0), null));
    }

    @Test
    void serverSupportsClientsAtOrBelowItsMinorVersion() {

        assertTrue(VersionCompatibility.serverSupportsClient(
                ProtocolVersion.of(1, 5), ProtocolVersion.of(1, 3)));

        assertFalse(VersionCompatibility.serverSupportsClient(
                ProtocolVersion.of(1, 2), ProtocolVersion.of(1, 3)));

        assertFalse(VersionCompatibility.serverSupportsClient(
                ProtocolVersion.of(2, 0), ProtocolVersion.of(1, 0)));
    }

}
