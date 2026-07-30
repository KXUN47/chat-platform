package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Protocol encoding or decoding failure. */
public class ProtocolException extends ChatException {
    private static final long serialVersionUID = 1L;
    public ProtocolException() { super(ErrorCode.INVALID_PACKET); }
    public ProtocolException(String message) { super(ErrorCode.INVALID_PACKET, message); }
    public ProtocolException(Throwable cause) { super(ErrorCode.INVALID_PACKET, cause); }
    public ProtocolException(String message, Throwable cause) { super(ErrorCode.INVALID_PACKET, message, cause); }
    public ProtocolException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.INVALID_PACKET, message, cause, details); }
}
