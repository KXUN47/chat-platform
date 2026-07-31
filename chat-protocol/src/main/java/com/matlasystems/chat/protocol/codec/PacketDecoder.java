package com.matlasystems.chat.protocol.codec;

import java.nio.ByteBuffer;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matlasystems.chat.protocol.exception.ProtocolException;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.validation.PacketValidator;
import com.matlasystems.chat.common.validation.ValidationResult;

/** Decodes framed JSON packets and verifies their required protocol metadata. */
public final class PacketDecoder {

    private final ObjectMapper objectMapper;
    private final FrameDecoder frameDecoder;
    private final PacketValidator packetValidator;

    public PacketDecoder() {
        this(PacketEncoder.defaultObjectMapper()
                        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES),
                new FrameDecoder(), new PacketValidator());
    }

    public PacketDecoder(ObjectMapper objectMapper, FrameDecoder frameDecoder,
                         PacketValidator packetValidator) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        this.frameDecoder = Objects.requireNonNull(frameDecoder, "frameDecoder must not be null");
        this.packetValidator = Objects.requireNonNull(packetValidator, "packetValidator must not be null");
    }

    public Packet decode(byte[] frame) {
        return decodeJson(frameDecoder.decode(frame));
    }

    public Optional<Packet> decode(ByteBuffer source) {
        return frameDecoder.decode(source).map(this::decodeJson);
    }

    public Packet decodeJson(byte[] json) {
        Objects.requireNonNull(json, "json must not be null");
        try {
            Packet packet = objectMapper.readValue(json, Packet.class);
            ValidationResult result = packetValidator.validate(packet);
            if (result.isInvalid()) {
                throw new ProtocolException("Invalid packet: " + result.getErrors());
            }
            return packet;
        } catch (IOException exception) {
            throw new ProtocolException("Unable to decode protocol packet", exception);
        }
    }
}
