package com.matlasystems.chat.common.protocol;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/** Envelope containing a protocol header and command-specific payload. */
public class Packet implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Header header;
    private Serializable payload;

    public Packet() {
    }

    public Packet(Header header, Serializable payload) {
        this.header = header;
        this.payload = payload;
    }

    public Header getHeader() { return header; }
    public void setHeader(Header header) { this.header = header; }
    public Object getPayload() { return payload; }
    public void setPayload(Serializable payload) { this.payload = payload; }

    public boolean isRequest() { return hasStatus(PacketStatus.REQUEST); }
    public boolean isResponse() { return hasStatus(PacketStatus.RESPONSE); }
    public boolean isEvent() { return hasStatus(PacketStatus.EVENT); }
    public boolean isError() { return hasStatus(PacketStatus.ERROR); }

    private boolean hasStatus(PacketStatus expected) {
        return header != null && header.getStatus() == expected;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof Packet other
                && Objects.equals(header, other.header));
    }

    @Override
    public int hashCode() { return Objects.hash(header); }

    @Override
    public String toString() { return "Packet{" + "header=" + header + ", payload=" + payload + '}'; }
}
