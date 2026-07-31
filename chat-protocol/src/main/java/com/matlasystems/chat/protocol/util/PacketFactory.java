package com.matlasystems.chat.protocol.util;

import java.io.Serializable;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.protocol.validator.ValidatorFactory;

/**
 * Creates protocol packets and validates them against
 * {@link ValidatorFactory#packetValidator()} before returning, so callers
 * never receive a packet that would fail validation downstream.
 */
public final class PacketFactory {

    private PacketFactory() {

        throw new UnsupportedOperationException("Utility class");

    }

    /**
     * Creates a request packet for the given command.
     */
    public static Packet request(CommandType command, Serializable payload) {

        return validated(new Packet(Header.create(command, PacketStatus.REQUEST), payload));

    }

    /**
     * Creates a response packet correlated with the given request.
     */
    public static Packet response(Packet request, Serializable payload) {

        return validated(new Packet(responseHeader(request, PacketStatus.RESPONSE), payload));

    }

    /**
     * Creates an error packet correlated with the given request.
     */
    public static Packet error(Packet request, Serializable payload) {

        return validated(new Packet(responseHeader(request, PacketStatus.ERROR), payload));

    }

    /**
     * Creates a server-initiated event packet.
     */
    public static Packet event(CommandType command, Serializable payload) {

        return validated(new Packet(Header.create(command, PacketStatus.EVENT), payload));

    }

    private static Header responseHeader(Packet request, PacketStatus status) {

        if (request == null || request.getHeader() == null) {
            throw new IllegalArgumentException("A request with a header is required.");
        }

        return HeaderUtils.deriveResponseHeader(request.getHeader(), status);

    }

    private static Packet validated(Packet packet) {

        ValidatorFactory.packetValidator().validateOrThrow(packet);

        return packet;

    }

}
