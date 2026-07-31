package com.matlasystems.chat.protocol.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.Packet;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link CommandDispatcher}.
 */
class CommandDispatcherTest {

    @Test
    void rejectsNullRegistryOrContext() {

        HandlerContext context =
                new HandlerContext();

        NullPointerException nullRegistryException =
                assertThrows(
                        NullPointerException.class,
                        () -> new CommandDispatcher(
                                null,
                                context));

        assertNotNull(
                nullRegistryException);

        HandlerRegistry registry =
                new HandlerRegistry();

        NullPointerException nullContextException =
                assertThrows(
                        NullPointerException.class,
                        () -> new CommandDispatcher(
                                registry,
                                null));

        assertNotNull(
                nullContextException);

    }

    @Test
    void dispatchesToTheRegisteredHandlerForTheHeaderCommand() {

        HandlerRegistry registry =
                new HandlerRegistry();

        HandlerContext context =
                new HandlerContext();

        registry.register(
                CommandType.PING,
                (packet, handlerContext) -> {

                    assertEquals(
                            context,
                            handlerContext);

                    return HandlerResult.success();

                });

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        registry,
                        context);

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        null);

        HandlerResult result =
                dispatcher.dispatch(
                        packet);

        assertTrue(
                result.isSuccess());

    }

    @Test
    void rejectsNullPacket() {

        HandlerRegistry registry =
                new HandlerRegistry();

        HandlerContext context =
                new HandlerContext();

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        registry,
                        context);

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> dispatcher.dispatch(
                                null));

        assertNotNull(
                exception);

    }

    @Test
    void rejectsAPacketWithoutAHeader() {

        HandlerRegistry registry =
                new HandlerRegistry();

        HandlerContext context =
                new HandlerContext();

        CommandDispatcher dispatcher =
                new CommandDispatcher(
                        registry,
                        context);

        Packet packet =
                new Packet(
                        null,
                        null);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> dispatcher.dispatch(
                                packet));

        assertNotNull(
                exception);

    }

}
