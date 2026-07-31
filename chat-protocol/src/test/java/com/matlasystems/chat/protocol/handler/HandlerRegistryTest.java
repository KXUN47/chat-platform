package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Unit tests for {@link HandlerRegistry}.
 */
class HandlerRegistryTest {

    @Test
    void registersAndFindsAHandler() {

        HandlerRegistry registry = new HandlerRegistry();
        CommandHandler handler = (packet, context) -> HandlerResult.success();

        registry.register(CommandType.PING, handler);

        assertTrue(registry.contains(CommandType.PING));
        assertEquals(handler, registry.find(CommandType.PING));
        assertEquals(1, registry.size());
    }

    @Test
    void fallsBackToUnsupportedCommandHandlerWhenMissing() {

        HandlerRegistry registry = new HandlerRegistry();

        assertInstanceOf(UnsupportedCommandHandler.class, registry.find(CommandType.PING));
    }

    @Test
    void unregisterAndClearRemoveHandlers() {

        HandlerRegistry registry = new HandlerRegistry();
        registry.register(CommandType.PING, (packet, context) -> HandlerResult.success());

        registry.unregister(CommandType.PING);
        assertFalse(registry.contains(CommandType.PING));

        registry.register(CommandType.PING, (packet, context) -> HandlerResult.success());
        registry.clear();
        assertEquals(0, registry.size());
    }

    @Test
    void getHandlersReturnsAllRegisteredHandlers() {

        HandlerRegistry registry = new HandlerRegistry();
        registry.register(CommandType.PING, (packet, context) -> HandlerResult.success());
        registry.register(CommandType.PONG, (packet, context) -> HandlerResult.success());

        assertEquals(2, registry.getHandlers().size());
    }

}
