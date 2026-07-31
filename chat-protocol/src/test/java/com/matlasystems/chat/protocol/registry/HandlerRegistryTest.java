package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.handler.CommandDispatcher;
import com.matlasystems.chat.protocol.handler.ErrorHandler;
import com.matlasystems.chat.protocol.handler.EventHandler;
import com.matlasystems.chat.protocol.handler.HandlerContext;
import com.matlasystems.chat.protocol.handler.ProtocolHandler;
import com.matlasystems.chat.protocol.handler.RequestHandler;
import com.matlasystems.chat.protocol.handler.ResponseHandler;

/**
 * Unit tests for the registry package's {@link HandlerRegistry}, which
 * indexes {@link ProtocolHandler} instances by command name.
 */
class HandlerRegistryTest {

    /**
     * Creates a protocol handler for testing.
     *
     * @return protocol handler
     */
    private ProtocolHandler newProtocolHandler() {

        HandlerContext context =
                new HandlerContext();

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        new com.matlasystems.chat.protocol.handler.HandlerRegistry(),
                        context);

        return new ProtocolHandler(
                new RequestHandler(
                        dispatcher),
                new ResponseHandler(
                        dispatcher),
                new EventHandler(
                        dispatcher),
                new ErrorHandler());

    }

    @Test
    void registersAndFindsAHandlerByCommandName() {

        HandlerRegistry registry =
                new HandlerRegistry();

        ProtocolHandler handler =
                newProtocolHandler();

        registry.register(
                "LOGIN",
                handler);

        assertTrue(
                registry.exists(
                        "LOGIN"));

        assertEquals(
                handler,
                registry.find(
                                "LOGIN")
                        .orElseThrow());

        assertEquals(
                1,
                registry.size());

    }

    @Test
    void rejectsDuplicateRegistration() {

        HandlerRegistry registry =
                new HandlerRegistry();

        registry.register(
                "LOGIN",
                newProtocolHandler());

        ProtocolHandler duplicateHandler =
                newProtocolHandler();

        DuplicateRegistrationException exception =
                assertThrows(
                        DuplicateRegistrationException.class,
                        () -> registry.register(
                                "LOGIN",
                                duplicateHandler));

        assertNotNull(
                exception);

    }

    @Test
    void removeAndClearEmptyTheRegistry() {

        HandlerRegistry registry =
                new HandlerRegistry();

        registry.register(
                "LOGIN",
                newProtocolHandler());

        registry.remove(
                "LOGIN");

        assertFalse(
                registry.exists(
                        "LOGIN"));

        registry.register(
                "LOGIN",
                newProtocolHandler());

        registry.clear();

        assertEquals(
                0,
                registry.size());

    }

    @Test
    void getAllReturnsEveryRegisteredHandler() {

        HandlerRegistry registry =
                new HandlerRegistry();

        registry.register(
                "LOGIN",
                newProtocolHandler());

        registry.register(
                "LOGOUT",
                newProtocolHandler());

        assertEquals(
                2,
                registry.getAll()
                        .size());

    }

}
