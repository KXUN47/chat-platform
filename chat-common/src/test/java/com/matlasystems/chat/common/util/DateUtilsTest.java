package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class DateUtilsTest {
    @Test void performsDateOperations() {
        assertNotNull(DateUtils.now());
        assertEquals(2, DateUtils.secondsBetween(Instant.EPOCH, Instant.EPOCH.plusSeconds(2)));
        assertEquals(Instant.EPOCH.plusSeconds(1), DateUtils.addSeconds(Instant.EPOCH, 1));
    }
}
