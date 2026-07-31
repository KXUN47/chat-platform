package com.matlasystems.chat.protocol.serializer;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.matlasystems.chat.common.protocol.Packet;

/** Serializes a packet as UTF-8 JSON. */
public final class JsonPacketSerializer implements PacketSerializer {

    private final ObjectMapper objectMapper;

    public JsonPacketSerializer() {
        this(defaultObjectMapper());
    }

    public JsonPacketSerializer(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public byte[] serialize(Packet packet) {
        Objects.requireNonNull(packet, "packet must not be null");
        try {
            return objectMapper.writeValueAsBytes(packet);
        } catch (JsonProcessingException exception) {
            throw new SerializationException("Unable to serialize packet as JSON", exception);
        }
    }

    static ObjectMapper defaultObjectMapper() {
        return new ObjectMapper().findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
