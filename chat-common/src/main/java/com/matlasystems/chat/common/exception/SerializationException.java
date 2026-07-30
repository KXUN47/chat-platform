package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Serialization or deserialization failure. */
public class SerializationException extends ChatException {
    private static final long serialVersionUID = 1L;
    public SerializationException() { super(ErrorCode.INVALID_PACKET); }
    public SerializationException(String message) { super(ErrorCode.INVALID_PACKET, message); }
    public SerializationException(Throwable cause) { super(ErrorCode.INVALID_PACKET, cause); }
    public SerializationException(String message, Throwable cause) { super(ErrorCode.INVALID_PACKET, message, cause); }
    public SerializationException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.INVALID_PACKET, message, cause, details); }
}
