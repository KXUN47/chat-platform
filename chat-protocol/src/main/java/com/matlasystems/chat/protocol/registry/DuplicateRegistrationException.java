package com.matlasystems.chat.protocol.registry;

/**
 * Thrown when attempting to register a component that already exists.
 */
public class DuplicateRegistrationException extends RegistryException {

    public DuplicateRegistrationException(String message) {
        super(message);
    }

    public DuplicateRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

}
