package com.matlasystems.chat.common.validation;

import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;

/**
 * Validates required metadata in a protocol packet.
 */
public final class PacketValidator
        implements Validator<Packet> {

    /**
     * Validation error code.
     */
    private static final String REQUIRED = "required";

    /**
     * Validation messages.
     */
    private static final String PACKET_REQUIRED =
            "Packet is required";

    private static final String HEADER_REQUIRED =
            "Packet header is required";

    private static final String PACKET_ID_REQUIRED =
            "Packet ID is required";

    private static final String TIMESTAMP_REQUIRED =
            "Packet timestamp is required";

    private static final String PROTOCOL_VERSION_REQUIRED =
            "Protocol version is required";

    private static final String COMMAND_REQUIRED =
            "Packet command is required";

    private static final String STATUS_REQUIRED =
            "Packet status is required";

    @Override
    public ValidationResult validate(Packet packet) {

        ValidationResult result = ValidationResult.valid();

        if (packet == null) {
            return result.addError(
                    "packet",
                    REQUIRED,
                    PACKET_REQUIRED);
        }

        Header header = packet.getHeader();

        if (header == null) {
            return result.addError(
                    "header",
                    REQUIRED,
                    HEADER_REQUIRED);
        }

        if (header.getPacketId() == null) {
            result.addError(
                    "packetId",
                    REQUIRED,
                    PACKET_ID_REQUIRED);
        }

        if (header.getTimestamp() == null) {
            result.addError(
                    "timestamp",
                    REQUIRED,
                    TIMESTAMP_REQUIRED);
        }

        if (header.getProtocolVersion() == null) {
            result.addError(
                    "protocolVersion",
                    REQUIRED,
                    PROTOCOL_VERSION_REQUIRED);
        }

        if (header.getCommand() == null) {
            result.addError(
                    "command",
                    REQUIRED,
                    COMMAND_REQUIRED);
        }

        if (header.getStatus() == null) {
            result.addError(
                    "status",
                    REQUIRED,
                    STATUS_REQUIRED);
        }

        return result;
    }
}
