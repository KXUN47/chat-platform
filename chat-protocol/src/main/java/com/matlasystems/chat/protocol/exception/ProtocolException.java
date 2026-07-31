package com.matlasystems.chat.protocol.exception;

import com.matlasystems.chat.common.enums.ErrorCode;
import com.matlasystems.chat.common.exception.ErrorDetails;

/** Base unchecked exception for protocol framing, encoding, and validation failures. */
public class ProtocolException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final ErrorCode errorCode;
    private final ErrorDetails errorDetails;
    public ProtocolException() { this(ErrorCode.INVALID_PACKET, null, null, null); }
    public ProtocolException(String message) { this(ErrorCode.INVALID_PACKET, message, null, null); }
    public ProtocolException(Throwable cause) { this(ErrorCode.INVALID_PACKET, null, cause, null); }
    public ProtocolException(String message, Throwable cause) { this(ErrorCode.INVALID_PACKET, message, cause, null); }
    public ProtocolException(String message, Throwable cause, ErrorDetails details) { this(ErrorCode.INVALID_PACKET, message, cause, details); }
    protected ProtocolException(ErrorCode code, String message, Throwable cause, ErrorDetails details) {
        super(message == null ? code.getMessage() : message, cause);
        this.errorCode = code == null ? ErrorCode.INVALID_PACKET : code;
        this.errorDetails = details;
    }
    public ErrorCode getErrorCode() { return errorCode; }
    public ErrorDetails getErrorDetails() { return errorDetails; }
}
