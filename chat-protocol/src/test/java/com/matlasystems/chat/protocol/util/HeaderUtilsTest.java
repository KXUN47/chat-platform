package com.matlasystems.chat.protocol.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.protocol.Header;
import com.matlasystems.chat.common.protocol.PacketStatus;

/**
 * Unit tests for {@link HeaderUtils}.
 */
class HeaderUtilsTest {

    @Test
    void createDelegatesToHeaderFactory() {

        Header header =
                HeaderUtils.create(
                        CommandType.PING,
                        PacketStatus.REQUEST);

        assertEquals(
                CommandType.PING,
                header.getCommand());

        assertEquals(
                PacketStatus.REQUEST,
                header.getStatus());

    }

    @Test
    void derivedResponseHeaderPreservesPacketIdAndCommand() {

        Header request =
                Header.create(
                        CommandType.PING,
                        PacketStatus.REQUEST);

        Header response =
                HeaderUtils.deriveResponseHeader(
                        request,
                        PacketStatus.RESPONSE);

        assertEquals(
                request.getPacketId(),
                response.getPacketId());

        assertEquals(
                request.getCommand(),
                response.getCommand());

        assertEquals(
                PacketStatus.RESPONSE,
                response.getStatus());

    }

    @Test
    void rejectsANullRequestHeader() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> HeaderUtils.deriveResponseHeader(
                                null,
                                PacketStatus.RESPONSE));

        assertNotNull(
                exception);

    }

    @Test
    void isCompleteReflectsPopulatedFields() {

        Header completeHeader =
                Header.create(
                        CommandType.PING,
                        PacketStatus.REQUEST);

        Header incompleteHeader =
                new Header(
                        null,
                        null,
                        null,
                        null,
                        null);

        assertTrue(
                HeaderUtils.isComplete(
                        completeHeader));

        assertFalse(
                HeaderUtils.isComplete(
                        incompleteHeader));

        assertFalse(
                HeaderUtils.isComplete(
                        null));

    }

    @Test
    void correlatesComparesPacketIds() {

        Header request =
                Header.create(
                        CommandType.PING,
                        PacketStatus.REQUEST);

        Header response =
                HeaderUtils.deriveResponseHeader(
                        request,
                        PacketStatus.RESPONSE);

        Header unrelated =
                Header.create(
                        CommandType.PING,
                        PacketStatus.REQUEST);

        assertTrue(
                HeaderUtils.correlates(
                        request,
                        response));

        assertFalse(
                HeaderUtils.correlates(
                        request,
                        unrelated));

        assertFalse(
                HeaderUtils.correlates(
                        null,
                        response));

    }

}
