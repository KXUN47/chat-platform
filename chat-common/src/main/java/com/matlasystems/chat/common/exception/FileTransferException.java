package com.matlasystems.chat.common.exception;
import com.matlasystems.chat.common.enums.ErrorCode;
/** File upload, download, or validation failure. */
public class FileTransferException extends ChatException {
    private static final long serialVersionUID = 1L;
    public FileTransferException() { super(ErrorCode.FILE_TRANSFER_FAILED); }
    public FileTransferException(String message) { super(ErrorCode.FILE_TRANSFER_FAILED, message); }
    public FileTransferException(Throwable cause) { super(ErrorCode.FILE_TRANSFER_FAILED, cause); }
    public FileTransferException(String message, Throwable cause) { super(ErrorCode.FILE_TRANSFER_FAILED, message, cause); }
    public FileTransferException(String message, Throwable cause, ErrorDetails details) { super(ErrorCode.FILE_TRANSFER_FAILED, message, cause, details); }
}
