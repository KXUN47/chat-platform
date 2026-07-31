package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;
import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Central coordinator for all protocol packets.
 *
 * <p>Determines the packet type and delegates processing to the
 * appropriate handler.</p>
 */
public class ProtocolHandler {

    private final RequestHandler requestHandler;

    private final ResponseHandler responseHandler;

    private final EventHandler eventHandler;

    private final ErrorHandler errorHandler;

    /**
     * Creates a new protocol handler.
     *
     * @param requestHandler request handler
     * @param responseHandler response handler
     * @param eventHandler event handler
     * @param errorHandler error handler
     */
    public ProtocolHandler(
            RequestHandler requestHandler,
            ResponseHandler responseHandler,
            EventHandler eventHandler,
            ErrorHandler errorHandler) {

        this.requestHandler = Objects.requireNonNull(
                requestHandler,
                "requestHandler cannot be null");

        this.responseHandler = Objects.requireNonNull(
                responseHandler,
                "responseHandler cannot be null");

        this.eventHandler = Objects.requireNonNull(
                eventHandler,
                "eventHandler cannot be null");

        this.errorHandler = Objects.requireNonNull(
                errorHandler,
                "errorHandler cannot be null");
    }

    /**
     * Processes an incoming protocol packet.
     *
     * @param packet incoming packet
     * @return handler result
     */
    public HandlerResult handle(
            Packet packet) {

        Objects.requireNonNull(
                packet,
                "packet cannot be null");

        Objects.requireNonNull(
                packet.getHeader(),
                "packet header cannot be null");

        try {

            PacketStatus status =
                    packet.getHeader().getStatus();

            return switch (status) {
                case REQUEST -> requestHandler.handle(packet);
                case RESPONSE -> responseHandler.handle(packet);
                case EVENT -> eventHandler.handle(packet);
                case ERROR -> errorHandler.handle(
                        new ProtocolException(
                                "Received ERROR packet."));
                default -> HandlerResult.failure(
                        "Unsupported packet status: "
                                + status);
            };

        } catch (Exception exception) {

            return errorHandler.handle(exception);

        }

    }

}
