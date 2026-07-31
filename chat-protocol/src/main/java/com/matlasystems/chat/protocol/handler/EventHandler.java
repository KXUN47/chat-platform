package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Handles protocol EVENT packets.
 */
public class EventHandler {

    private final CommandDispatcher dispatcher;

    /**
     * Creates a new event handler.
     *
     * @param dispatcher command dispatcher
     */
    public EventHandler(
            CommandDispatcher dispatcher) {

        this.dispatcher = Objects.requireNonNull(
                dispatcher,
                "dispatcher cannot be null");
    }

    /**
     * Processes an EVENT packet.
     *
     * @param packet event packet
     * @return handler result
     */
    public HandlerResult handle(
            Packet packet) {

        Objects.requireNonNull(
                packet,
                "packet cannot be null");

        if (!packet.isEvent()) {
            throw new ProtocolException(
                    "Packet is not an EVENT.");
        }

        return dispatcher.dispatch(packet);
    }

}
