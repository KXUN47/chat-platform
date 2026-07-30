package com.matlasystems.chat.common.exception;

import com.matlasystems.chat.common.enums.ErrorCode;

/** Base unchecked exception for expected chat platform failures. */
public class ChatException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final ErrorCode errorCode;
    private final ErrorDetails errorDetails;

    public ChatException(ErrorCode errorCode) { this(errorCode, null, null, null); }
    public ChatException(ErrorCode errorCode, String message) { this(errorCode, message, null, null); }
    public ChatException(ErrorCode errorCode, Throwable cause) { this(errorCode, null, cause, null); }
    public ChatException(ErrorCode errorCode, String message, Throwable cause) { this(errorCode, message, cause, null); }

    public ChatException(ErrorCode errorCode, String message, Throwable cause, ErrorDetails errorDetails) {
        super(message == null ? defaultMessage(errorCode) : message, cause);
        this.errorCode = errorCode == null ? ErrorCode.UNKNOWN_ERROR : errorCode;
        this.errorDetails = errorDetails;
    }

    private static String defaultMessage(ErrorCode errorCode) {
        return (errorCode == null ? ErrorCode.UNKNOWN_ERROR : errorCode).getMessage();
    }

    public ErrorCode getErrorCode() { return errorCode; }
    public ErrorDetails getErrorDetails() { return errorDetails; }
}
