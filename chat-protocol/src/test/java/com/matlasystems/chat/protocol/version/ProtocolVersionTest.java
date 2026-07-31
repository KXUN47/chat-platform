package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ProtocolVersion}.
 */
class ProtocolVersionTest {

    @Test
    void currentIsV1_0_0() {

        ProtocolVersion expectedVersion =
                ProtocolVersion.of(
                        1,
                        0,
                        0);

        ProtocolVersion actualVersion =
                ProtocolVersion.current();

        assertEquals(
                expectedVersion,
                actualVersion);

    }

    @Test
    void ofWithoutPatchDefaultsToZero() {

        ProtocolVersion expectedVersion =
                ProtocolVersion.of(
                        2,
                        1,
                        0);

        ProtocolVersion actualVersion =
                ProtocolVersion.of(
                        2,
                        1);

        assertEquals(
                expectedVersion,
                actualVersion);

    }

    @Test
    void rejectsNegativeComponents() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> ProtocolVersion.of(
                                -1,
                                0,
                                0));

        assertNotNull(
                exception);

    }

    @Test
    void comparesByMajorThenMinorThenPatch() {

        ProtocolVersion version200 =
                ProtocolVersion.of(
                        2,
                        0,
                        0);

        ProtocolVersion version199 =
                ProtocolVersion.of(
                        1,
                        9,
                        9);

        ProtocolVersion version120 =
                ProtocolVersion.of(
                        1,
                        2,
                        0);

        ProtocolVersion version119 =
                ProtocolVersion.of(
                        1,
                        1,
                        9);

        ProtocolVersion version112 =
                ProtocolVersion.of(
                        1,
                        1,
                        2);

        ProtocolVersion version111 =
                ProtocolVersion.of(
                        1,
                        1,
                        1);

        ProtocolVersion anotherVersion111 =
                ProtocolVersion.of(
                        1,
                        1,
                        1);

        assertTrue(
                version200.compareTo(
                        version199) > 0);

        assertTrue(
                version120.compareTo(
                        version119) > 0);

        assertTrue(
                version112.compareTo(
                        version111) > 0);

        assertEquals(
                0,
                version111.compareTo(
                        anotherVersion111));

    }

    @Test
    void equalityAndHashCodeAreBasedOnComponents() {

        ProtocolVersion first =
                ProtocolVersion.of(
                        1,
                        2,
                        3);

        ProtocolVersion second =
                ProtocolVersion.of(
                        1,
                        2,
                        3);

        ProtocolVersion different =
                ProtocolVersion.of(
                        1,
                        2,
                        4);

        assertEquals(
                first,
                second);

        assertEquals(
                first.hashCode(),
                second.hashCode());

        assertNotEquals(
                first,
                different);

        assertNotEquals(
                "1.2.3",
                first);

    }

    @Test
    void isSameMajorComparesOnlyTheMajorComponent() {

        ProtocolVersion version100 =
                ProtocolVersion.of(
                        1,
                        0,
                        0);

        ProtocolVersion version199 =
                ProtocolVersion.of(
                        1,
                        9,
                        9);

        ProtocolVersion version200 =
                ProtocolVersion.of(
                        2,
                        0,
                        0);

        assertTrue(
                version100.isSameMajor(
                        version199));

        assertFalse(
                version100.isSameMajor(
                        version200));

        assertFalse(
                version100.isSameMajor(
                        null));

    }

    @Test
    void toStringUsesDottedFormat() {

        ProtocolVersion version =
                ProtocolVersion.of(
                        1,
                        2,
                        3);

        assertEquals(
                "1.2.3",
                version.toString());

    }

}
