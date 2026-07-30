package com.matlasystems.chat.common.event.file;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a file upload starts.
 */
public final class FileUploadStartedEvent
        extends ApplicationEvent {

    private final UUID transferId;

    private final Long userId;

    private final Long fileId;

    /**
     * Creates a new file upload started event.
     *
     * @param transferId transfer identifier
     * @param userId user identifier
     * @param fileId file identifier
     */
    public FileUploadStartedEvent(
            UUID transferId,
            Long userId,
            Long fileId) {

        super(
                EventType.FILE_UPLOAD_STARTED,
                EventPriority.NORMAL,
                "file");

        this.transferId = transferId;
        this.userId = userId;
        this.fileId = fileId;
    }

    /**
     * Returns the transfer identifier.
     *
     * @return transfer ID
     */
    public UUID getTransferId() {
        return transferId;
    }

    /**
     * Returns the user identifier.
     *
     * @return user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the file identifier.
     *
     * @return file ID
     */
    public Long getFileId() {
        return fileId;
    }

}
