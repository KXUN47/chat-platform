package com.matlasystems.chat.protocol.codec;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/** Decodes one complete length-prefixed frame from a byte buffer. */
public final class FrameDecoder {

    public Optional<byte[]> decode(ByteBuffer source) {
        Objects.requireNonNull(source, "source must not be null");
        if (source.remaining() < Integer.BYTES) {
            return Optional.empty();
        }

        source.mark();
        int length = source.getInt();
        if (length < 0 || length > ValidationConstants.MAX_PACKET_SIZE_BYTES) {
            throw new ProtocolException("Invalid packet frame length: " + length);
        }
        if (source.remaining() < length) {
            source.reset();
            return Optional.empty();
        }

        byte[] payload = new byte[length];
        source.get(payload);
        return Optional.of(payload);
    }

    public byte[] decode(byte[] frame) {
        Objects.requireNonNull(frame, "frame must not be null");
        ByteBuffer buffer = ByteBuffer.wrap(frame);
        byte[] payload = decode(buffer)
                .orElseThrow(() -> new ProtocolException("Incomplete packet frame"));
        if (buffer.hasRemaining()) {
            throw new ProtocolException("Frame contains trailing bytes");
        }
        return payload;
    }
}
