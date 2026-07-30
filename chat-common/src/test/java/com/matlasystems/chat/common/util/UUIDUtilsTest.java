package com.matlasystems.chat.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link UUIDUtils}.
 */
class UUIDUtilsTest {

    @Test
    void parsesUuids() {

        UUID uuid =
                UUID.randomUUID();

        String value =
                uuid.toString();

        assertTrue(
                UUIDUtils.isValid(value));

        assertEquals(
                uuid,
                UUIDUtils.parse(value)
                        .orElseThrow());

        assertTrue(
                UUIDUtils.parse("bad")
                        .isEmpty());
    }

}
