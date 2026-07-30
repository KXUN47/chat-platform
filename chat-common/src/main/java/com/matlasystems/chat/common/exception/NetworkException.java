package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** Network communication failure. */
public class NetworkException extends ChatException {
    private static final long serialVersionUID = 1L;
    public NetworkException() { super(ErrorCode.NETWORK_ERROR); }
    public NetworkException(String message) { super(ErrorCode.NETWORK_ERROR, message); }
    public NetworkException(Throwable cause) { super(ErrorCode.NETWORK_ERROR, cause); }
    public NetworkException(String message, Throwable cause) { super(ErrorCode.NETWORK_ERROR, message, cause); }
    public NetworkException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.NETWORK_ERROR, message, cause, details); }
}
