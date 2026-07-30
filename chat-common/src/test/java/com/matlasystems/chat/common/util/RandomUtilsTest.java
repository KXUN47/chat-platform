package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class RandomUtilsTest {
    @Test void generatesValuesWithinExpectedBounds() {
        assertTrue(RandomUtils.nextInt(5)<5);
        assertEquals(8,RandomUtils.nextBytes(8).length);
        assertEquals(10,RandomUtils.numeric(10).length());
        assertEquals(5,RandomUtils.randomFrom(List.of(5)));
    }
}
