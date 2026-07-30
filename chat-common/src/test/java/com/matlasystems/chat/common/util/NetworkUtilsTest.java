package com.matlasystems.chat.common.util;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NetworkUtilsTest {
    @Test void validatesNetworkValues() {
        assertTrue(NetworkUtils.isValidPort(8080));
        assertFalse(NetworkUtils.isValidPort(0));
        assertTrue(NetworkUtils.isValidIp("127.0.0.1"));
        assertFalse(NetworkUtils.isValidIp(""));
    }
}
