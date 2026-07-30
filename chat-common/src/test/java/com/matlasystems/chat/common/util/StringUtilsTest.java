package com.matlasystems.chat.common.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

    @Test
    void performsNullSafeStringOperations() {

        assertTrue(StringUtils.isBlank(" "));
        assertEquals("a b", StringUtils.normalizeWhitespace(" a  b "));
        assertEquals("abc", StringUtils.truncate("abcd", 3));
        assertNull(StringUtils.trimToNull(" "));
    }

}
