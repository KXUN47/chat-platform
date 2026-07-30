package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Requested resource does not exist. */
public class ResourceNotFoundException extends ChatException {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException() { super(ErrorCode.RECORD_NOT_FOUND); }
    public ResourceNotFoundException(String message) { super(ErrorCode.RECORD_NOT_FOUND, message); }
    public ResourceNotFoundException(Throwable cause) { super(ErrorCode.RECORD_NOT_FOUND, cause); }
    public ResourceNotFoundException(String message, Throwable cause) { super(ErrorCode.RECORD_NOT_FOUND, message, cause); }
    public ResourceNotFoundException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.RECORD_NOT_FOUND, message, cause, details); }
}
