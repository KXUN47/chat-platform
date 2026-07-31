package com.matlasystems.chat.protocol.serializer;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matlasystems.chat.common.protocol.Packet;

/** Deserializes a packet from UTF-8 JSON. */
public final class JsonPacketDeserializer implements PacketDeserializer {

    private final ObjectMapper objectMapper;

    public JsonPacketDeserializer() {
        this(JsonPacketSerializer.defaultObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }

    public JsonPacketDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public Packet deserialize(byte[] data) {
        Objects.requireNonNull(data, "data must not be null");
        try {
            return objectMapper.readValue(data, Packet.class);
        } catch (IOException exception) {
            throw new SerializationException("Unable to deserialize JSON packet", exception);
        }
    }
}
