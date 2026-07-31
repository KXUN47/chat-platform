package com.matlasystems.chat.protocol.handler;

import com.matlasystems.chat.common.protocol.Packet;

/**
 * Base contract implemented by every protocol command.
 */
@FunctionalInterface
public interface CommandHandler {

    HandlerResult handle(
            Packet packet,
            HandlerContext context);

}
