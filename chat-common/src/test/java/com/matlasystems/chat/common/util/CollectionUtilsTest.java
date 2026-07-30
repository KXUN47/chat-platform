package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CollectionUtilsTest {
    @Test void handlesCollections() {
        assertTrue(CollectionUtils.isEmpty(null));
        assertEquals("a", CollectionUtils.first(List.of("a")));
        assertEquals(List.of("a"), CollectionUtils.removeNulls(Arrays.asList("a", null)));
    }
}
