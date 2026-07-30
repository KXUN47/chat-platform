package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Resource conflicts with an existing record. */
public class DuplicateResourceException extends ChatException {
    private static final long serialVersionUID = 1L;
    public DuplicateResourceException() { super(ErrorCode.DUPLICATE_RECORD); }
    public DuplicateResourceException(String message) { super(ErrorCode.DUPLICATE_RECORD, message); }
    public DuplicateResourceException(Throwable cause) { super(ErrorCode.DUPLICATE_RECORD, cause); }
    public DuplicateResourceException(String message, Throwable cause) { super(ErrorCode.DUPLICATE_RECORD, message, cause); }
    public DuplicateResourceException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.DUPLICATE_RECORD, message, cause, details); }
}
