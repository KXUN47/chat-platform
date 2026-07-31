package com.matlasystems.chat.protocol.registry;

/**
 * Thrown when a requested component
 * cannot be found in a registry.
 */
public class ComponentNotFoundException
        extends RegistryException {

    public ComponentNotFoundException(String message) {
        super(message);
    }

    public ComponentNotFoundException(
            String message,
            Throwable cause) {

        super(message, cause);

    }

}
