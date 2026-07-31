package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;

/**
 * Dispatches protocol packets to the appropriate command handler.
 */
public class CommandDispatcher {

    private final HandlerRegistry registry;

    private final HandlerContext context;

    public CommandDispatcher(
            HandlerRegistry registry,
            HandlerContext context) {

        this.registry = Objects.requireNonNull(
                registry,
                "registry cannot be null");

        this.context = Objects.requireNonNull(
                context,
                "context cannot be null");

    }

    /**
     * Dispatches a packet to its registered handler.
     *
     * @param packet protocol packet
     * @return handler result
     */
    public HandlerResult dispatch(Packet packet) {

        Objects.requireNonNull(
                packet,
                "packet cannot be null");

        Header header = packet.getHeader();

        if (header == null) {

            throw new IllegalArgumentException(
                    "Packet header cannot be null.");

        }

        CommandType command = header.getCommand();

        CommandHandler handler =
                registry.find(command);

        return handler.handle(packet, context);

    }

}
