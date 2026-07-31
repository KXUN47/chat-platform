package com.matlasystems.chat.protocol.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Unit tests for {@link CommandMetadata}.
 */
class CommandMetadataTest {

    @Test
    void exposesConstructorValues() {

        CommandMetadata metadata = new CommandMetadata(
                "Send Message",
                "Sends a chat message",
                4096,
                5000L,
                false,
                null,
                ProtocolVersion.current());

        assertEquals("Send Message", metadata.getDisplayName());
        assertEquals("Sends a chat message", metadata.getDescription());
        assertEquals(4096, metadata.getMaxPayloadSize());
        assertEquals(5000L, metadata.getTimeoutMillis());
        assertFalse(metadata.isDeprecated());
        assertEquals(ProtocolVersion.current(), metadata.getSinceVersion());
        assertTrue(metadata.toString().contains("Send Message"));
    }

    @Test
    void tracksDeprecationReplacement() {

        CommandMetadata metadata = new CommandMetadata(
                "Old Broadcast",
                "Deprecated broadcast command",
                1024,
                1000L,
                true,
                "SEND_MESSAGE",
                ProtocolVersion.current());

        assertTrue(metadata.isDeprecated());
        assertEquals("SEND_MESSAGE", metadata.getReplacementCommand());
    }

}
