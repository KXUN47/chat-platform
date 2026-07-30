package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Authentication failure. */
public class AuthenticationException extends ChatException {
    private static final long serialVersionUID = 1L;
    public AuthenticationException() { super(ErrorCode.AUTHENTICATION_FAILED); }
    public AuthenticationException(String message) { super(ErrorCode.AUTHENTICATION_FAILED, message); }
    public AuthenticationException(Throwable cause) { super(ErrorCode.AUTHENTICATION_FAILED, cause); }
    public AuthenticationException(String message, Throwable cause) { super(ErrorCode.AUTHENTICATION_FAILED, message, cause); }
    public AuthenticationException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.AUTHENTICATION_FAILED, message, cause, details); }
}
