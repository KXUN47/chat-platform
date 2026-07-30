package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Database persistence failure. */
public class DatabaseException extends ChatException {
    private static final long serialVersionUID = 1L;
    public DatabaseException() { super(ErrorCode.DATABASE_ERROR); }
    public DatabaseException(String message) { super(ErrorCode.DATABASE_ERROR, message); }
    public DatabaseException(Throwable cause) { super(ErrorCode.DATABASE_ERROR, cause); }
    public DatabaseException(String message, Throwable cause) { super(ErrorCode.DATABASE_ERROR, message, cause); }
    public DatabaseException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.DATABASE_ERROR, message, cause, details); }
}
