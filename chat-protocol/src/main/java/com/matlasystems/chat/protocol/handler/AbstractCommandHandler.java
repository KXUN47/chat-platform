package com.matlasystems.chat.protocol.handler;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;

/**
 * Template implementation for protocol command handlers.
 */
public abstract class AbstractCommandHandler
        implements CommandHandler {

    @Override
    public final HandlerResult handle(
            Packet packet,
            HandlerContext context) {

        validatePacket(packet);

        validateHeader(packet.getHeader());

        validateContext(context);

        beforeHandle(packet, context);

        HandlerResult result =
                doHandle(packet, context);

        afterHandle(packet, context, result);

        return result;

    }

    /**
     * Business implementation.
     */
    protected abstract HandlerResult doHandle(
            Packet packet,
            HandlerContext context);

    /**
     * Hook before processing.
     */
    protected void beforeHandle(
            Packet packet,
            HandlerContext context) {
    }

    /**
     * Hook after processing.
     */
    protected void afterHandle(
            Packet packet,
            HandlerContext context,
            HandlerResult result) {
    }

    /**
     * Validates packet.
     */
    protected void validatePacket(Packet packet) {

        Objects.requireNonNull(
                packet,
                "packet cannot be null");

    }

    /**
     * Validates protocol header.
     */
    protected void validateHeader(Header header) {

        Objects.requireNonNull(
                header,
                "header cannot be null");

        Objects.requireNonNull(
                header.getPacketId(),
                "packetId cannot be null");

        Objects.requireNonNull(
                header.getTimestamp(),
                "timestamp cannot be null");

        Objects.requireNonNull(
                header.getProtocolVersion(),
                "protocolVersion cannot be null");

        Objects.requireNonNull(
                header.getCommand(),
                "command cannot be null");

        Objects.requireNonNull(
                header.getStatus(),
                "status cannot be null");

    }

    /**
     * Validates handler context.
     */
    protected void validateContext(
            HandlerContext context) {

        Objects.requireNonNull(
                context,
                "context cannot be null");

    }

}
