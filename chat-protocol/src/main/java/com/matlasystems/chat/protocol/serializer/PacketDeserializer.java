package com.matlasystems.chat.protocol.serializer;

import com.matlasystems.chat.common.protocol.Packet;

/** Deserializes protocol packet envelopes without TCP framing. */
public interface PacketDeserializer extends Deserializer<Packet> {
}
