package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link VersionManager}.
 */
class VersionManagerTest {

    private VersionManager createVersionManager() {

        VersionManager versionManager =
                new VersionManager();

        versionManager.register(
                VersionMetadata.of(
                        ProtocolVersion.of(
                                1,
                                0),
                        "Initial release"));

        versionManager.register(
                VersionMetadata.of(
                        ProtocolVersion.of(
                                1,
                                5),
                        "Typing indicators"));

        return versionManager;

    }

    @Test
    void tracksRegisteredVersionsAndMetadata() {

        VersionManager manager =
                createVersionManager();

        ProtocolVersion version100 =
                ProtocolVersion.of(
                        1,
                        0);

        ProtocolVersion version200 =
                ProtocolVersion.of(
                        2,
                        0);

        assertTrue(
                manager.isSupported(
                        version100));

        assertFalse(
                manager.isSupported(
                        version200));

        assertEquals(
                "Initial release",
                manager.metadataFor(
                                version100)
                        .orElseThrow()
                        .getDescription());

        assertEquals(
                2,
                manager.getSupportedVersions()
                        .size());

    }

    @Test
    void latestVersionReturnsTheHighestRegisteredVersion() {

        VersionManager manager =
                createVersionManager();

        ProtocolVersion expectedVersion =
                ProtocolVersion.of(
                        1,
                        5);

        assertEquals(
                expectedVersion,
                manager.latestVersion());

    }

    @Test
    void latestVersionThrowsWhenNothingIsRegistered() {

        VersionManager manager =
                new VersionManager();

        UnsupportedProtocolVersionException exception =
                assertThrows(
                        UnsupportedProtocolVersionException.class,
                        manager::latestVersion);

        assertNotNull(
                exception);

    }

    @Test
    void negotiatesASupportedVersionForARequest() {

        VersionManager manager =
                createVersionManager();

        ProtocolVersion requestedVersion =
                ProtocolVersion.of(
                        1,
                        2);

        ProtocolVersion expectedVersion =
                ProtocolVersion.of(
                        1,
                        5);

        assertEquals(
                expectedVersion,
                manager.negotiate(
                        requestedVersion));

    }

    @Test
    void negotiateThrowsWhenNoCompatibleVersionIsRegistered() {

        VersionManager manager =
                createVersionManager();

        ProtocolVersion unsupportedVersion =
                ProtocolVersion.of(
                        9,
                        0);

        UnsupportedProtocolVersionException exception =
                assertThrows(
                        UnsupportedProtocolVersionException.class,
                        () -> manager.negotiate(
                                unsupportedVersion));

        assertNotNull(
                exception);

    }

    @Test
    void tracksFeatureSupportThroughTheFeatureRegistry() {

        VersionManager manager =
                createVersionManager();

        ProtocolVersion featureVersion =
                ProtocolVersion.of(
                        1,
                        5);

        ProtocolVersion unsupportedVersion =
                ProtocolVersion.of(
                        1,
                        0);

        manager.getFeatureRegistry()
                .register(
                        "TYPING_INDICATORS",
                        featureVersion);

        assertTrue(
                manager.supportsFeature(
                        "TYPING_INDICATORS",
                        featureVersion));

        assertFalse(
                manager.supportsFeature(
                        "TYPING_INDICATORS",
                        unsupportedVersion));

    }

    @Test
    void currentVersionDefaultsToProtocolVersionCurrent() {

        VersionManager manager =
                createVersionManager();

        assertEquals(
                ProtocolVersion.current(),
                manager.getCurrentVersion());

    }

    @Test
    void setCurrentVersionRequiresARegisteredVersion() {

        VersionManager manager =
                createVersionManager();

        ProtocolVersion supportedVersion =
                ProtocolVersion.of(
                        1,
                        5);

        manager.setCurrentVersion(
                supportedVersion);

        assertEquals(
                supportedVersion,
                manager.getCurrentVersion());

        ProtocolVersion unsupportedVersion =
                ProtocolVersion.of(
                        9,
                        9);

        UnsupportedProtocolVersionException exception =
                assertThrows(
                        UnsupportedProtocolVersionException.class,
                        () -> manager.setCurrentVersion(
                                unsupportedVersion));

        assertNotNull(
                exception);

    }

    @Test
    void clearRemovesVersionsAndFeatures() {

        VersionManager manager =
                createVersionManager();

        manager.getFeatureRegistry()
                .register(
                        "FEATURE",
                        ProtocolVersion.of(
                                1,
                                0));

        manager.clear();

        assertTrue(
                manager.getSupportedVersions()
                        .isEmpty());

        assertTrue(
                manager.getFeatureRegistry()
                        .isEmpty());

    }

}
