package com.matlasystems.chat.common.protocol;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.matlasystems.chat.common.enums.CommandType;

/** Metadata shared by every packet transmitted over the chat protocol. */
public class Header implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID packetId;
    private Instant timestamp;
    private ProtocolVersion protocolVersion;
    private CommandType command;
    private PacketStatus status;

    public Header() {
    }

    public Header(UUID packetId, Instant timestamp, ProtocolVersion protocolVersion,
                  CommandType command, PacketStatus status) {
        this.packetId = packetId;
        this.timestamp = timestamp;
        this.protocolVersion = protocolVersion;
        this.command = command;
        this.status = status;
    }

    public static Header create(CommandType command, PacketStatus status) {
        return new Header(UUID.randomUUID(), Instant.now(), ProtocolVersion.current(), command, status);
    }

    public UUID getPacketId() { return packetId; }
    public void setPacketId(UUID packetId) { this.packetId = packetId; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public ProtocolVersion getProtocolVersion() { return protocolVersion; }
    public void setProtocolVersion(ProtocolVersion protocolVersion) { this.protocolVersion = protocolVersion; }
    public CommandType getCommand() { return command; }
    public void setCommand(CommandType command) { this.command = command; }
    public PacketStatus getStatus() { return status; }
    public void setStatus(PacketStatus status) { this.status = status; }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof Header other
                && Objects.equals(packetId, other.packetId));
    }

    @Override
    public int hashCode() { return Objects.hash(packetId); }

    @Override
    public String toString() {
        return "Header{" + "packetId=" + packetId + ", command=" + command
                + ", status=" + status + ", protocolVersion=" + protocolVersion + '}';
    }
}
