package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link VersionMetadata}.
 */
class VersionMetadataTest {

    @Test
    void exposesConstructorValues() {

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        Instant releasedAt =
                Instant.parse(
                        "2026-01-01T00:00:00Z");

        VersionMetadata metadata =
                VersionMetadata.of(
                        version,
                        "Initial release",
                        false,
                        releasedAt);

        assertEquals(
                version,
                metadata.getVersion());

        assertEquals(
                "Initial release",
                metadata.getDescription());

        assertFalse(
                metadata.isDeprecated());

        assertEquals(
                releasedAt,
                metadata.getReleasedAt());

    }

    @Test
    void convenienceFactoryDefaultsToNotDeprecatedWithATimestamp() {

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        VersionMetadata metadata =
                VersionMetadata.of(
                        version,
                        "Initial release");

        assertFalse(
                metadata.isDeprecated());

        assertNotNull(
                metadata.getReleasedAt());

    }

    @Test
    void rejectsANullVersion() {

        Instant releasedAt =
                Instant.now();

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> VersionMetadata.of(
                                null,
                                "description",
                                false,
                                releasedAt));

        assertNotNull(
                exception);

    }

    @Test
    void equalityIsBasedOnVersionAlone() {

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        Instant releasedAt =
                Instant.now();

        VersionMetadata first =
                VersionMetadata.of(
                        version,
                        "a",
                        false,
                        releasedAt);

        VersionMetadata second =
                VersionMetadata.of(
                        version,
                        "b",
                        true,
                        Instant.EPOCH);

        assertEquals(
                first,
                second);

        assertEquals(
                first.hashCode(),
                second.hashCode());

    }

    @Test
    void toStringContainsVersionAndDeprecationState() {

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        Instant releasedAt =
                Instant.now();

        VersionMetadata metadata =
                VersionMetadata.of(
                        version,
                        "a",
                        true,
                        releasedAt);

        String metadataText =
                metadata.toString();

        assertTrue(
                metadataText.contains(
                        "1.0.0"));

        assertTrue(
                metadataText.contains(
                        "true"));

    }

}
