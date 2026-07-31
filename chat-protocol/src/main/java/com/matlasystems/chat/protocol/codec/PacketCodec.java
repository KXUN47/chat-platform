package com.matlasystems.chat.protocol.codec;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Optional;

import com.matlasystems.chat.common.protocol.Packet;

/** Convenience facade that combines packet encoding and decoding. */
public final class PacketCodec {

    private final PacketEncoder encoder;
    private final PacketDecoder decoder;

    public PacketCodec() {
        this(new PacketEncoder(), new PacketDecoder());
    }

    public PacketCodec(PacketEncoder encoder, PacketDecoder decoder) {
        this.encoder = Objects.requireNonNull(encoder, "encoder must not be null");
        this.decoder = Objects.requireNonNull(decoder, "decoder must not be null");
    }

    public byte[] encode(Packet packet) { return encoder.encode(packet); }
    public Packet decode(byte[] frame) { return decoder.decode(frame); }
    public Optional<Packet> decode(ByteBuffer source) { return decoder.decode(source); }
}
