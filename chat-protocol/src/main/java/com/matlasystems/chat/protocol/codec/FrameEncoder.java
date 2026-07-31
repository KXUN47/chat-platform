package com.matlasystems.chat.protocol.codec;

import java.nio.ByteBuffer;
import java.util.Objects;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/** Encodes a payload using the protocol's four-byte big-endian length prefix. */
public final class FrameEncoder {

    public byte[] encode(byte[] payload) {
        Objects.requireNonNull(payload, "payload must not be null");
        if (payload.length > ValidationConstants.MAX_PACKET_SIZE_BYTES) {
            throw new ProtocolException("Packet exceeds the maximum allowed size");
        }

        return ByteBuffer.allocate(Integer.BYTES + payload.length)
                .putInt(payload.length)
                .put(payload)
                .array();
    }
}
