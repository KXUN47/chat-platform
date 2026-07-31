package com.matlasystems.chat.protocol.codec;

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.matlasystems.chat.protocol.exception.ProtocolException;
import com.matlasystems.chat.common.protocol.Packet;

/** Serializes protocol packets as UTF-8 JSON and frames them for TCP transport. */
public final class PacketEncoder {

    private final ObjectMapper objectMapper;
    private final FrameEncoder frameEncoder;

    public PacketEncoder() {
        this(defaultObjectMapper(), new FrameEncoder());
    }

    public PacketEncoder(ObjectMapper objectMapper, FrameEncoder frameEncoder) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        this.frameEncoder = Objects.requireNonNull(frameEncoder, "frameEncoder must not be null");
    }

    public byte[] encode(Packet packet) {
        return frameEncoder.encode(encodeJson(packet));
    }

    public byte[] encodeJson(Packet packet) {
        Objects.requireNonNull(packet, "packet must not be null");
        try {
            return objectMapper.writeValueAsBytes(packet);
        } catch (JsonProcessingException exception) {
            throw new ProtocolException("Unable to encode protocol packet", exception);
        }
    }

    static ObjectMapper defaultObjectMapper() {
        return new ObjectMapper().findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
