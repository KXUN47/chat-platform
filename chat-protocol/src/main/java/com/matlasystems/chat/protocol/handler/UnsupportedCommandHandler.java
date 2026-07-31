package com.matlasystems.chat.protocol.handler;

import com.matlasystems.chat.common.protocol.Packet;

/**
 * Default handler used when no handler is registered
 * for a protocol command.
 */
public class UnsupportedCommandHandler
        extends AbstractCommandHandler {

    @Override
    protected HandlerResult doHandle(
            Packet packet,
            HandlerContext context) {

        String command = packet.getHeader()
                               .getCommand()
                               .name();

        return HandlerResult.failure(
                "Unsupported command: " + command);

    }

}
