package com.matlasystems.chat.protocol.serializer;

import java.io.IOException;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/** Deserializes a command payload from UTF-8 JSON. */
public final class JsonPayloadDeserializer implements PayloadDeserializer {

    private final ObjectMapper objectMapper;

    public JsonPayloadDeserializer() {
        this(JsonPacketSerializer.defaultObjectMapper()
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }

    public JsonPayloadDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
    }

    @Override
    public Object deserialize(byte[] data) {
        return deserialize(data, Object.class);
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> targetType) {
        Objects.requireNonNull(data, "data must not be null");
        Objects.requireNonNull(targetType, "targetType must not be null");
        try {
            return objectMapper.readValue(data, targetType);
        } catch (IOException exception) {
            throw new SerializationException("Unable to deserialize JSON payload", exception);
        }
    }
}
