package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.Socket;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.protocol.ProtocolVersion;

/**
 * Unit tests for {@link HandlerContext}.
 */
class HandlerContextTest {

    @Test
    void storesAndClearsState() {

        HandlerContext context =
                new HandlerContext();

        Socket socket =
                new Socket();

        UUID userId =
                UUID.randomUUID();

        ProtocolVersion protocolVersion =
                ProtocolVersion.current();

        context.setSocket(
                socket);

        context.setUserId(
                userId);

        context.setProtocolVersion(
                protocolVersion);

        context.setAuthenticated(
                true);

        assertEquals(
                socket,
                context.getSocket());

        assertEquals(
                userId,
                context.getUserId());

        assertEquals(
                protocolVersion,
                context.getProtocolVersion());

        assertTrue(
                context.isAuthenticated());

        context.clear();

        assertNull(
                context.getSocket());

        assertNull(
                context.getUserId());

        assertNull(
                context.getProtocolVersion());

        assertFalse(
                context.isAuthenticated());

    }

    @Test
    void validateRequiresSocketAndProtocolVersion() {

        HandlerContext context =
                new HandlerContext();

        NullPointerException missingSocketException =
                assertThrows(
                        NullPointerException.class,
                        context::validate);

        assertNotNull(
                missingSocketException);

        context.setSocket(
                new Socket());

        NullPointerException missingProtocolVersionException =
                assertThrows(
                        NullPointerException.class,
                        context::validate);

        assertNotNull(
                missingProtocolVersionException);

        context.setProtocolVersion(
                ProtocolVersion.current());

        assertDoesNotThrow(
                context::validate);

    }

}
