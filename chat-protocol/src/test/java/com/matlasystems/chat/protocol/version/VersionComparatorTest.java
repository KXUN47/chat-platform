package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link VersionComparator}.
 */
class VersionComparatorTest {

    @Test
    void ascendingOrdersOldestFirst() {

        assertTrue(VersionComparator.ASCENDING.compare(
                ProtocolVersion.of(1, 0), ProtocolVersion.of(2, 0)) < 0);
    }

    @Test
    void descendingOrdersNewestFirst() {

        assertTrue(VersionComparator.DESCENDING.compare(
                ProtocolVersion.of(1, 0), ProtocolVersion.of(2, 0)) > 0);
    }

    @Test
    void majorOnlyIgnoresMinorAndPatch() {

        assertEquals(0, VersionComparator.MAJOR_ONLY.compare(
                ProtocolVersion.of(1, 0, 0), ProtocolVersion.of(1, 9, 9)));
    }

    @Test
    void ascendingSortsAListCorrectly() {

        List<ProtocolVersion> versions = new java.util.ArrayList<>(List.of(
                ProtocolVersion.of(2, 0), ProtocolVersion.of(1, 0), ProtocolVersion.of(1, 5)));

        versions.sort(VersionComparator.ASCENDING);

        assertEquals(List.of(
                ProtocolVersion.of(1, 0), ProtocolVersion.of(1, 5), ProtocolVersion.of(2, 0)), versions);
    }

}
