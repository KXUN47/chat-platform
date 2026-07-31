package com.matlasystems.chat.protocol.version;

import com.matlasystems.chat.protocol.exception.ProtocolException;

/**
 * Thrown when a protocol version is not supported by this application,
 * or when no mutually compatible version can be negotiated.
 */
public class UnsupportedProtocolVersionException extends ProtocolException {

    private static final long serialVersionUID = 1L;

    private final transient ProtocolVersion requestedVersion;

    public UnsupportedProtocolVersionException(ProtocolVersion requestedVersion) {

        super("Unsupported protocol version: " + requestedVersion);

        this.requestedVersion = requestedVersion;

    }

    public UnsupportedProtocolVersionException(ProtocolVersion requestedVersion, Throwable cause) {

        super("Unsupported protocol version: " + requestedVersion, cause);

        this.requestedVersion = requestedVersion;

    }

    /**
     * Returns the version that could not be supported or negotiated.
     */
    public ProtocolVersion getRequestedVersion() {

        return requestedVersion;

    }

}
