package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Handles protocol RESPONSE packets.
 *
 * Response packets are received after a remote peer has processed
 * a previously sent request.
 */
public class ResponseHandler {

    private final CommandDispatcher dispatcher;

    /**
     * Creates a new response handler.
     *
     * @param dispatcher dispatcher responsible for routing response packets
     */
    public ResponseHandler(
            CommandDispatcher dispatcher) {

        this.dispatcher = Objects.requireNonNull(
                dispatcher,
                "dispatcher must not be null");

    }

    /**
     * Processes a protocol response packet.
     *
     * @param packet response packet to process
     * @return handler result
     * @throws ProtocolException if the packet is not a response packet
     */
    public HandlerResult handle(
            Packet packet) {

        Objects.requireNonNull(
                packet,
                "packet must not be null");

        if (!packet.isResponse()) {

            throw new ProtocolException(
                    "Packet is not a RESPONSE.");

        }

        return dispatcher.dispatch(packet);

    }

}
