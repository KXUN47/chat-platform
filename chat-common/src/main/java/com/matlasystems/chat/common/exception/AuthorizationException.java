package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Authorization failure. */
public class AuthorizationException extends ChatException {
    private static final long serialVersionUID = 1L;
    public AuthorizationException() { super(ErrorCode.ACCESS_DENIED); }
    public AuthorizationException(String message) { super(ErrorCode.ACCESS_DENIED, message); }
    public AuthorizationException(Throwable cause) { super(ErrorCode.ACCESS_DENIED, cause); }
    public AuthorizationException(String message, Throwable cause) { super(ErrorCode.ACCESS_DENIED, message, cause); }
    public AuthorizationException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.ACCESS_DENIED, message, cause, details); }
}
