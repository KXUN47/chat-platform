package com.matlasystems.chat.protocol.version;

import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Base unchecked exception for protocol version failures.
 */
public class VersionException extends ProtocolException {

    private static final long serialVersionUID = 1L;

    public VersionException(String message) {

        super(message);

    }

    public VersionException(String message, Throwable cause) {

        super(message, cause);

    }

}
