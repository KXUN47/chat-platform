package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Requested command is not supported. */
public class CommandNotFoundException extends ChatException {
    private static final long serialVersionUID = 1L;
    public CommandNotFoundException() { super(ErrorCode.INVALID_COMMAND); }
    public CommandNotFoundException(String message) { super(ErrorCode.INVALID_COMMAND, message); }
    public CommandNotFoundException(Throwable cause) { super(ErrorCode.INVALID_COMMAND, cause); }
    public CommandNotFoundException(String message, Throwable cause) { super(ErrorCode.INVALID_COMMAND, message, cause); }
    public CommandNotFoundException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.INVALID_COMMAND, message, cause, details); }
}
