package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Session lifecycle failure. */
public class SessionException extends ChatException {
    private static final long serialVersionUID = 1L;
    public SessionException() { super(ErrorCode.SESSION_EXPIRED); }
    public SessionException(String message) { super(ErrorCode.SESSION_EXPIRED, message); }
    public SessionException(Throwable cause) { super(ErrorCode.SESSION_EXPIRED, cause); }
    public SessionException(String message, Throwable cause) { super(ErrorCode.SESSION_EXPIRED, message, cause); }
    public SessionException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.SESSION_EXPIRED, message, cause, details); }
}
