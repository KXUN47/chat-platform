package com.matlasystems.chat.protocol.version;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.enums.ErrorCode;

/**
 * Unit tests for {@link UnsupportedProtocolVersionException}.
 */
class UnsupportedProtocolVersionExceptionTest {

    @Test
    void tracksTheRejectedVersionAndMessage() {

        UnsupportedProtocolVersionException exception =
                new UnsupportedProtocolVersionException(ProtocolVersion.of(9, 9));

        assertEquals(ProtocolVersion.of(9, 9), exception.getRequestedVersion());
        assertTrue(exception.getMessage().contains("9.9.0"));
        assertEquals(ErrorCode.INVALID_PACKET, exception.getErrorCode());
    }

    @Test
    void carriesAnOptionalCause() {

        Throwable cause = new RuntimeException("boom");

        UnsupportedProtocolVersionException exception =
                new UnsupportedProtocolVersionException(ProtocolVersion.of(1, 0), cause);

        assertEquals(cause, exception.getCause());
    }

}
