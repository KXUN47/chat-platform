package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Input validation failure. */
public class ValidationException extends ChatException {
    private static final long serialVersionUID = 1L;
    public ValidationException() { super(ErrorCode.INVALID_REQUEST); }
    public ValidationException(String message) { super(ErrorCode.INVALID_REQUEST, message); }
    public ValidationException(Throwable cause) { super(ErrorCode.INVALID_REQUEST, cause); }
    public ValidationException(String message, Throwable cause) { super(ErrorCode.INVALID_REQUEST, message, cause); }
    public ValidationException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.INVALID_REQUEST, message, cause, details); }
}
