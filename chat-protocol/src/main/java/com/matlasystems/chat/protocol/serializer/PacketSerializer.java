package com.matlasystems.chat.protocol.serializer;

import com.matlasystems.chat.common.protocol.Packet;

/** Serializes protocol packet envelopes without TCP framing. */
public interface PacketSerializer extends Serializer<Packet> {
}
