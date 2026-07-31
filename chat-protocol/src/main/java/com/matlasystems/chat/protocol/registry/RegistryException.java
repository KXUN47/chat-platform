package com.matlasystems.chat.protocol.registry;

public class RegistryException extends RuntimeException {

    public RegistryException(String message) {
        super(message);
    }

    public RegistryException(String message,
                             Throwable cause) {
        super(message, cause);
    }

}
