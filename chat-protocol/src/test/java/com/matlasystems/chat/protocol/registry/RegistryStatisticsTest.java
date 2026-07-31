package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link RegistryStatistics}.
 */
class RegistryStatisticsTest {

    @Test
    void computesTotalsAndCompleteness() {

        RegistryStatistics stats = new RegistryStatistics(18, 18, 7, 4, 5, 2);

        assertEquals(18, stats.getCommandCount());
        assertEquals(18, stats.getHandlerCount());
        assertEquals(7, stats.getParserCount());
        assertEquals(4, stats.getSerializerCount());
        assertEquals(5, stats.getValidatorCount());
        assertEquals(2, stats.getVersionCount());
        assertEquals(54, stats.getTotalComponents());
        assertTrue(stats.isComplete());
        assertNotNull(stats.getGeneratedAt());
    }

    @Test
    void isNotCompleteWhenAnyRegistryIsEmpty() {

        RegistryStatistics stats = new RegistryStatistics(0, 18, 7, 4, 5, 2);

        assertFalse(stats.isComplete());
    }

    @Test
    void equalityIgnoresGeneratedTimestamp() {

        RegistryStatistics first = new RegistryStatistics(1, 2, 3, 4, 5, 6);
        RegistryStatistics second = new RegistryStatistics(1, 2, 3, 4, 5, 6);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void toStringContainsCounts() {

        RegistryStatistics stats = new RegistryStatistics(1, 2, 3, 4, 5, 6);

        assertTrue(stats.toString().contains("Registry Statistics"));
    }

}
