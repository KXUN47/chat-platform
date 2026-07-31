package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link FeatureRegistry}.
 */
class FeatureRegistryTest {

    @Test
    void registersAndLooksUpAFeature() {

        FeatureRegistry registry =
                new FeatureRegistry();

        ProtocolVersion introducedVersion =
                ProtocolVersion.of(
                        1,
                        2);

        registry.register(
                "TYPING_INDICATORS",
                introducedVersion);

        assertEquals(
                introducedVersion,
                registry.introducedIn(
                                "TYPING_INDICATORS")
                        .orElseThrow());

        assertEquals(
                1,
                registry.size());

        assertFalse(
                registry.isEmpty());

    }

    @Test
    void rejectsBlankFeatureNameOrNullVersion() {

        FeatureRegistry registry =
                new FeatureRegistry();

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        IllegalArgumentException blankNameException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(
                                " ",
                                version));

        assertNotNull(
                blankNameException);

        IllegalArgumentException nullVersionException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(
                                "FEATURE",
                                null));

        assertNotNull(
                nullVersionException);

    }

    @Test
    void isSupportedOnceVersionMeetsOrExceedsIntroducedVersion() {

        FeatureRegistry registry =
                new FeatureRegistry();

        ProtocolVersion introducedVersion =
                ProtocolVersion.of(
                        1,
                        2);

        registry.register(
                "TYPING_INDICATORS",
                introducedVersion);

        assertTrue(
                registry.isSupported(
                        "TYPING_INDICATORS",
                        ProtocolVersion.of(
                                1,
                                2)));

        assertTrue(
                registry.isSupported(
                        "TYPING_INDICATORS",
                        ProtocolVersion.of(
                                1,
                                3)));

        assertFalse(
                registry.isSupported(
                        "TYPING_INDICATORS",
                        ProtocolVersion.of(
                                1,
                                1)));

        assertFalse(
                registry.isSupported(
                        "UNKNOWN_FEATURE",
                        ProtocolVersion.of(
                                1,
                                2)));

        assertFalse(
                registry.isSupported(
                        "TYPING_INDICATORS",
                        null));

    }

    @Test
    void removeAndClearEmptyTheRegistry() {

        FeatureRegistry registry =
                new FeatureRegistry();

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        registry.register(
                "FEATURE",
                version);

        registry.remove(
                "FEATURE");

        assertTrue(
                registry.isEmpty());

        registry.register(
                "FEATURE",
                version);

        registry.clear();

        assertTrue(
                registry.isEmpty());

    }

    @Test
    void getRegisteredFeaturesIsUnmodifiable() {

        FeatureRegistry registry =
                new FeatureRegistry();

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        0);

        registry.register(
                "FEATURE",
                version);

        Map<String, ProtocolVersion> registeredFeatures =
                registry.getRegisteredFeatures();

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        registeredFeatures::clear);

        assertNotNull(
                exception);

    }

}
