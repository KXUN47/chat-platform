package com.matlasystems.chat.protocol.validator;

import java.io.Serializable;

/** Validates that a payload can be transferred by the protocol. */
public final class PayloadValidator implements Validator<Object> {

    @Override
    public ValidationResult validate(Object payload) {
        if (payload == null) {
            return ValidationResult.valid();
        }
        return payload instanceof Serializable
                ? ValidationResult.valid()
                : ValidationResult.invalid("payload", "serializable", "Payload must implement Serializable");
    }
}
