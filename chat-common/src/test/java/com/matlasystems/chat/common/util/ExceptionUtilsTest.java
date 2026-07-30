package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExceptionUtilsTest {
    @Test void extractsExceptionDetails() {
        IllegalArgumentException root=new IllegalArgumentException("root");
        RuntimeException wrapped=new RuntimeException("wrapped",root);

        assertSame(root,ExceptionUtils.rootCause(wrapped));
        assertEquals("root",ExceptionUtils.rootCauseMessage(wrapped));
        assertTrue(ExceptionUtils.stackTrace(wrapped).contains("wrapped"));
    }
}
