package com.matlasystems.chat.protocol.util;

import java.nio.charset.StandardCharsets;

import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.protocol.serializer.JsonPacketDeserializer;
import com.matlasystems.chat.protocol.serializer.JsonPacketSerializer;
import com.matlasystems.chat.protocol.serializer.JsonPayloadDeserializer;
import com.matlasystems.chat.protocol.serializer.JsonPayloadSerializer;

/**
 * String-based JSON convenience wrappers around the byte-oriented protocol
 * serializers, for packets and their payloads.
 */
public final class JsonUtils {

    private static final JsonPacketSerializer PACKET_SERIALIZER = new JsonPacketSerializer();
    private static final JsonPacketDeserializer PACKET_DESERIALIZER = new JsonPacketDeserializer();
    private static final JsonPayloadSerializer PAYLOAD_SERIALIZER = new JsonPayloadSerializer();
    private static final JsonPayloadDeserializer PAYLOAD_DESERIALIZER = new JsonPayloadDeserializer();

    private JsonUtils() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Converts a packet to its JSON string representation.
     */
    public static String toJson(Packet packet) {

        return new String(PACKET_SERIALIZER.serialize(packet), StandardCharsets.UTF_8);

    }

    /**
     * Parses a packet from its JSON string representation.
     */
    public static Packet packetFromJson(String json) {

        return PACKET_DESERIALIZER.deserialize(json.getBytes(StandardCharsets.UTF_8));

    }

    /**
     * Converts a payload to its JSON string representation.
     */
    public static String payloadToJson(Object payload) {

        return new String(PAYLOAD_SERIALIZER.serialize(payload), StandardCharsets.UTF_8);

    }

    /**
     * Parses a payload from its JSON string representation into the given type.
     */
    public static <T> T payloadFromJson(String json, Class<T> type) {

        return PAYLOAD_DESERIALIZER.deserialize(json.getBytes(StandardCharsets.UTF_8), type);

    }

}
