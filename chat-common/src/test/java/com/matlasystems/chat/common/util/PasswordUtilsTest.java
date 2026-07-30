package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PasswordUtilsTest {
    @Test void evaluatesPasswordStrength() {
        assertTrue(PasswordUtils.isStrong("StrongPass1!"));
        assertFalse(PasswordUtils.isStrong("weak"));
        assertTrue(PasswordUtils.secureEquals("a","a"));
        assertEquals(12,PasswordUtils.generateTemporaryPassword().length());
    }
}
