package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PacketIdGenerator}.
 */
class PacketIdGeneratorTest {

    @Test
    void generatesARandomIdentifier() {

        UUID packetId =
                PacketIdGenerator.generate();

        assertNotNull(
                packetId);

    }

    @Test
    void parsesAValidUuidString() {

        UUID id =
                UUID.randomUUID();

        UUID parsedId =
                PacketIdGenerator.parse(
                        id.toString());

        assertEquals(
                id,
                parsedId);

    }

    @Test
    void rejectsBlankInput() {

        IllegalArgumentException blankInputException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> PacketIdGenerator.parse(
                                " "));

        assertNotNull(
                blankInputException);

        IllegalArgumentException nullInputException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> PacketIdGenerator.parse(
                                null));

        assertNotNull(
                nullInputException);

    }

}
