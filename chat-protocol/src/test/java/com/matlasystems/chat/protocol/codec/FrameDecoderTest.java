package com.matlasystems.chat.protocol.codec;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.ByteBuffer;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Unit tests for {@link FrameDecoder}.
 */
class FrameDecoderTest {

    private final FrameEncoder encoder =
            new FrameEncoder();

    private final FrameDecoder decoder =
            new FrameDecoder();

    @Test
    void decodesACompleteFrame() {

        byte[] payload =
                "hello".getBytes();

        byte[] decoded =
                decoder.decode(
                        encoder.encode(payload));

        assertArrayEquals(
                payload,
                decoded);

    }

    @Test
    void returnsEmptyWhenLengthPrefixIsIncomplete() {

        ByteBuffer buffer =
                ByteBuffer.wrap(
                        new byte[] {0, 0});

        Optional<byte[]> result =
                decoder.decode(buffer);

        assertTrue(
                result.isEmpty());

    }

    @Test
    void returnsEmptyAndResetsPositionWhenPayloadIsIncomplete() {

        byte[] frame =
                encoder.encode(
                        "hello world".getBytes());

        ByteBuffer partial =
                ByteBuffer.wrap(
                        frame,
                        0,
                        Integer.BYTES + 2);

        Optional<byte[]> result =
                decoder.decode(partial);

        assertTrue(
                result.isEmpty());

        assertEquals(
                0,
                partial.position());

    }

    @Test
    void rejectsNegativeLength() {

        ByteBuffer buffer =
                ByteBuffer.allocate(Integer.BYTES)
                        .putInt(-1)
                        .flip();

        ProtocolException exception =
                assertThrows(
                        ProtocolException.class,
                        () -> decoder.decode(buffer));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void rejectsIncompleteStandaloneFrame() {

        byte[] frame =
                ByteBuffer.allocate(Integer.BYTES)
                        .putInt(10)
                        .array();

        ProtocolException exception =
                assertThrows(
                        ProtocolException.class,
                        () -> decoder.decode(frame));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void rejectsTrailingBytesInStandaloneFrame() {

        byte[] frame =
                encoder.encode(
                        "hi".getBytes());

        byte[] withTrailer =
                new byte[frame.length + 1];

        System.arraycopy(
                frame,
                0,
                withTrailer,
                0,
                frame.length);

        ProtocolException exception =
                assertThrows(
                        ProtocolException.class,
                        () -> decoder.decode(withTrailer));

        assertNotNull(
                exception.getMessage());

    }

    @Test
    void decodeAcceptsMultipleFramesSequentially() {

        byte[] first =
                encoder.encode(
                        "first".getBytes());

        byte[] second =
                encoder.encode(
                        "second".getBytes());

        ByteBuffer buffer =
                ByteBuffer.allocate(
                                first.length + second.length)
                        .put(first)
                        .put(second)
                        .flip();

        assertArrayEquals(
                "first".getBytes(),
                decoder.decode(buffer).orElseThrow());

        assertArrayEquals(
                "second".getBytes(),
                decoder.decode(buffer).orElseThrow());

        assertFalse(
                buffer.hasRemaining());

    }

}
