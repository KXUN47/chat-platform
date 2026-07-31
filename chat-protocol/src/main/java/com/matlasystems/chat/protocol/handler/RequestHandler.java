package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Handles incoming REQUEST packets.
 *
 * <p>A request packet represents an operation initiated by a client
 * that expects the server to process the command and optionally
 * return a response.</p>
 *
 * <p>Examples:</p>
 * <ul>
 *     <li>LOGIN</li>
 *     <li>REGISTER</li>
 *     <li>SEND_MESSAGE</li>
 *     <li>PRIVATE_MESSAGE</li>
 *     <li>USER_LIST</li>
 *     <li>FILE_UPLOAD</li>
 * </ul>
 */
public class RequestHandler {

    private final CommandDispatcher dispatcher;

    public RequestHandler(CommandDispatcher dispatcher) {

        this.dispatcher = Objects.requireNonNull(
                dispatcher,
                "dispatcher cannot be null");

    }

    /**
     * Processes a REQUEST packet.
     *
     * @param packet incoming request packet
     * @return handler result
     * @throws ProtocolException if the packet is not a request
     */
    public HandlerResult handle(Packet packet) {

        Objects.requireNonNull(
                packet,
                "packet cannot be null");

        if (!packet.isRequest()) {

            throw new ProtocolException(
                    "Packet is not a REQUEST.");

        }

        return dispatcher.dispatch(packet);

    }

}
