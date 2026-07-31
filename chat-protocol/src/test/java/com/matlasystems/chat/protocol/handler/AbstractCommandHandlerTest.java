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
 * Unit tests for {@link AbstractCommandHandler}.
 */
class AbstractCommandHandlerTest {

    /**
     * Test implementation of {@link AbstractCommandHandler}.
     */
    private static final class RecordingHandler
            extends AbstractCommandHandler {

        private boolean beforeCalled;

        private boolean afterCalled;

        @Override
        protected HandlerResult doHandle(
                Packet packet,
                HandlerContext context) {

            return HandlerResult.success();

        }

        @Override
        protected void beforeHandle(
                Packet packet,
                HandlerContext context) {

            beforeCalled = true;

        }

        @Override
        protected void afterHandle(
                Packet packet,
                HandlerContext context,
                HandlerResult result) {

            afterCalled = true;

        }

    }

    @Test
    void invokesLifecycleHooksAroundDoHandle() {

        RecordingHandler handler =
                new RecordingHandler();

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        null);

        HandlerContext context =
                new HandlerContext();

        HandlerResult result =
                handler.handle(
                        packet,
                        context);

        assertTrue(
                result.isSuccess());

        assertTrue(
                handler.beforeCalled);

        assertTrue(
                handler.afterCalled);

    }

    @Test
    void rejectsAPacketWithAnIncompleteHeader() {

        RecordingHandler handler =
                new RecordingHandler();

        Packet packet =
                new Packet(
                        new Header(
                                null,
                                null,
                                null,
                                null,
                                null),
                        null);

        HandlerContext context =
                new HandlerContext();

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> handler.handle(
                                packet,
                                context));

        assertNotNull(
                exception);

    }

    @Test
    void rejectsANullContext() {

        RecordingHandler handler =
                new RecordingHandler();

        Packet packet =
                new Packet(
                        Header.create(
                                CommandType.PING,
                                PacketStatus.REQUEST),
                        null);

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> handler.handle(
                                packet,
                                null));

        assertNotNull(
                exception);

    }

    @Test
    void rejectsANullPacket() {

        RecordingHandler handler =
                new RecordingHandler();

        HandlerContext context =
                new HandlerContext();

        NullPointerException exception =
                assertThrows(
                        NullPointerException.class,
                        () -> handler.handle(
                                null,
                                context));

        assertEquals(
                NullPointerException.class,
                exception.getClass());

    }

}
