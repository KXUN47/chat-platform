package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Application configuration failure. */
public class ConfigurationException extends ChatException {
    private static final long serialVersionUID = 1L;
    public ConfigurationException() { super(ErrorCode.INTERNAL_SERVER_ERROR); }
    public ConfigurationException(String message) { super(ErrorCode.INTERNAL_SERVER_ERROR, message); }
    public ConfigurationException(Throwable cause) { super(ErrorCode.INTERNAL_SERVER_ERROR, cause); }
    public ConfigurationException(String message, Throwable cause) { super(ErrorCode.INTERNAL_SERVER_ERROR, message, cause); }
    public ConfigurationException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.INTERNAL_SERVER_ERROR, message, cause, details); }
}
