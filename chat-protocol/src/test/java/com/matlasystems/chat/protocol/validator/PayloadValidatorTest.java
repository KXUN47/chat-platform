package com.matlasystems.chat.protocol.validator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link PayloadValidator}.
 */
class PayloadValidatorTest {

    private final PayloadValidator validator = new PayloadValidator();

    @Test
    void treatsANullPayloadAsValid() {

        assertTrue(validator.validate(null).isValid());
    }

    @Test
    void acceptsASerializablePayload() {

        assertTrue(validator.validate("hello").isValid());
    }

    @Test
    void rejectsANonSerializablePayload() {

        Object nonSerializable = new Object();

        assertTrue(validator.validate(nonSerializable).isInvalid());
    }

}
