package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.entity.Session;
import com.matlasystems.chat.common.enums.SessionStatus;

/**
 * Unit tests for {@link SessionValidator}.
 */
class SessionValidatorTest {

    @Test
    void validatesSession() {

        SessionValidator validator =
                new SessionValidator();

        Session session =
                new Session(
                        UUID.randomUUID(),
                        1L,
                        Instant.now(),
                        Instant.now(),
                        "127.0.0.1",
                        8080,
                        SessionStatus.ACTIVE);

        ValidationResult validResult =
                validator.validate(session);

        assertTrue(
                validResult.isValid());

        session.setPort(0);

        ValidationResult invalidResult =
                validator.validate(session);

        assertTrue(
                invalidResult.isInvalid());
    }

}
