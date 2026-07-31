package com.matlasystems.chat.protocol.codec;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.ByteBuffer;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.constants.ValidationConstants;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link FrameEncoder}.
 */
class FrameEncoderTest {

    private final FrameEncoder encoder = new FrameEncoder();

    @Test
    void prefixesPayloadWithFourByteBigEndianLength() {

        byte[] payload = "hello".getBytes();

        byte[] frame = encoder.encode(payload);

        ByteBuffer buffer = ByteBuffer.wrap(frame);

        assertEquals(payload.length, buffer.getInt());
        assertEquals(Integer.BYTES + payload.length, frame.length);

        byte[] remaining = new byte[payload.length];
        buffer.get(remaining);

        assertArrayEquals(payload, remaining);
    }

    @Test
    void rejectsPayloadLargerThanMaxPacketSize() {

        byte[] oversized = new byte[ValidationConstants.MAX_PACKET_SIZE_BYTES + 1];

        assertThrows(ProtocolException.class, () -> encoder.encode(oversized));
    }

    @Test
    void rejectsNullPayload() {

        assertThrows(NullPointerException.class, () -> encoder.encode(null));
    }

}
