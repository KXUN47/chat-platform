package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Client exceeded an operation rate limit. */
public class RateLimitException extends ChatException {
    private static final long serialVersionUID = 1L;
    public RateLimitException() { super(ErrorCode.RATE_LIMIT_EXCEEDED); }
    public RateLimitException(String message) { super(ErrorCode.RATE_LIMIT_EXCEEDED, message); }
    public RateLimitException(Throwable cause) { super(ErrorCode.RATE_LIMIT_EXCEEDED, cause); }
    public RateLimitException(String message, Throwable cause) { super(ErrorCode.RATE_LIMIT_EXCEEDED, message, cause); }
    public RateLimitException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.RATE_LIMIT_EXCEEDED, message, cause, details); }
}
