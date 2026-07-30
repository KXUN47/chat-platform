package com.matlasystems.chat.common.protocol;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ProtocolVersion}.
 */
class ProtocolVersionTest {

    @Test
    void resolvesKnownVersions() {

        assertEquals(
                ProtocolVersion.V1_0,
                ProtocolVersion.fromValue("1.0")
                        .orElseThrow());

        assertTrue(
                ProtocolVersion.fromValue("unknown")
                        .isEmpty());

        assertEquals(
                "1.0",
                ProtocolVersion.current()
                        .getValue());
    }

}
