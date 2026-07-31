package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.serializer.JsonPacketSerializer;

/**
 * Unit tests for {@link SerializerRegistry}.
 */
class SerializerRegistryTest {

    @Test
    void registersAndFindsASerializer() {

        SerializerRegistry registry =
                new SerializerRegistry();

        JsonPacketSerializer serializer =
                new JsonPacketSerializer();

        registry.register(
                JsonPacketSerializer.class,
                serializer);

        assertTrue(
                registry.exists(
                        JsonPacketSerializer.class));

        assertEquals(
                serializer,
                registry.find(
                                JsonPacketSerializer.class)
                        .orElseThrow());

        assertEquals(
                1,
                registry.size());

    }

    @Test
    void rejectsNullTypeOrSerializer() {

        SerializerRegistry registry =
                new SerializerRegistry();

        JsonPacketSerializer serializer =
                new JsonPacketSerializer();

        IllegalArgumentException nullTypeException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(
                                null,
                                serializer));

        assertNotNull(
                nullTypeException);

        IllegalArgumentException nullSerializerException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(
                                JsonPacketSerializer.class,
                                null));

        assertNotNull(
                nullSerializerException);

    }

    @Test
    void rejectsDuplicateRegistration() {

        SerializerRegistry registry =
                new SerializerRegistry();

        registry.register(
                JsonPacketSerializer.class,
                new JsonPacketSerializer());

        JsonPacketSerializer duplicateSerializer =
                new JsonPacketSerializer();

        DuplicateRegistrationException exception =
                assertThrows(
                        DuplicateRegistrationException.class,
                        () -> registry.register(
                                JsonPacketSerializer.class,
                                duplicateSerializer));

        assertNotNull(
                exception);

    }

    @Test
    void removeAndClearEmptyTheRegistry() {

        SerializerRegistry registry =
                new SerializerRegistry();

        registry.register(
                JsonPacketSerializer.class,
                new JsonPacketSerializer());

        registry.remove(
                JsonPacketSerializer.class);

        assertEquals(
                0,
                registry.size());

        registry.register(
                JsonPacketSerializer.class,
                new JsonPacketSerializer());

        registry.clear();

        assertEquals(
                0,
                registry.size());

    }

    @Test
    void getAllReturnsEveryRegisteredSerializer() {

        SerializerRegistry registry =
                new SerializerRegistry();

        registry.register(
                JsonPacketSerializer.class,
                new JsonPacketSerializer());

        assertEquals(
                1,
                registry.getAll()
                        .size());

    }

}
