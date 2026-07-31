package com.matlasystems.chat.protocol.serializer;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Serializes a command payload as UTF-8 JSON. */
public final class JsonPayloadSerializer implements PayloadSerializer {

    private final ObjectMapper objectMapper;

    public JsonPayloadSerializer() {
        this(JsonPacketSerializer.defaultObjectMapper());
    }

    public JsonPayloadSerializer(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public byte[] serialize(Object payload) {
        try {
            return objectMapper.writeValueAsBytes(payload);
        } catch (JsonProcessingException exception) {
            throw new SerializationException("Unable to serialize JSON payload", exception);
        }
    }
}
