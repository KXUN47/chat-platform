package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.matlasystems.chat.common.entity.FileMetadata;

/**
 * Represents the state of an active file upload session.
 * <p>
 * An upload session tracks:
 * <ul>
 *     <li>The unique transfer identifier</li>
 *     <li>The uploading user</li>
 *     <li>The uploaded file metadata</li>
 *     <li>The current transfer progress</li>
 *     <li>The session creation timestamp</li>
 *     <li>The session completion timestamp</li>
 * </ul>
 */
public class UploadSession implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID transferId;

    private Long uploaderId;

    private FileMetadata fileMetadata;

    private FileTransferProgress progress;

    private Instant createdAt;

    private Instant completedAt;

    /**
     * Creates an empty upload session.
     * <p>
     * Required for serialization frameworks.
     */
    public UploadSession() {
        // Required for serialization frameworks.
    }

    /**
     * Creates a new upload session.
     *
     * @param uploaderId uploader identifier
     * @param fileMetadata uploaded file metadata
     */
    public UploadSession(
            Long uploaderId,
            FileMetadata fileMetadata) {

        this.transferId = UUID.randomUUID();
        this.uploaderId = uploaderId;
        this.fileMetadata = fileMetadata;

        this.progress = new FileTransferProgress(
                transferId,
                fileMetadata == null
                        ? 0L
                        : fileMetadata.getFileSize());

        this.createdAt = Instant.now();
    }

    /**
     * Marks the upload as completed.
     */
    public void complete() {

        if (progress != null) {
            progress.updateTransferredBytes(
                    progress.getTotalBytes());
        }

        completedAt = Instant.now();
    }

    /**
     * Determines whether the upload has completed.
     *
     * @return {@code true} if completed; otherwise {@code false}
     */
    public boolean isCompleted() {

        return completedAt != null
                || (progress != null && progress.isCompleted());
    }

    /**
     * Returns the transfer identifier.
     *
     * @return transfer identifier
     */
    public UUID getTransferId() {
        return transferId;
    }

    /**
     * Sets the transfer identifier.
     *
     * @param transferId transfer identifier
     */
    public void setTransferId(UUID transferId) {
        this.transferId = transferId;
    }

    /**
     * Returns the uploader identifier.
     *
     * @return uploader identifier
     */
    public Long getUploaderId() {
        return uploaderId;
    }

    /**
     * Sets the uploader identifier.
     *
     * @param uploaderId uploader identifier
     */
    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    /**
     * Returns the uploaded file metadata.
     *
     * @return file metadata
     */
    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }

    /**
     * Sets the uploaded file metadata.
     *
     * @param fileMetadata file metadata
     */
    public void setFileMetadata(FileMetadata fileMetadata) {
        this.fileMetadata = fileMetadata;
    }

    /**
     * Returns the transfer progress.
     *
     * @return transfer progress
     */
    public FileTransferProgress getProgress() {
        return progress;
    }

    /**
     * Sets the transfer progress.
     *
     * @param progress transfer progress
     */
    public void setProgress(FileTransferProgress progress) {
        this.progress = progress;
    }

    /**
     * Returns the session creation timestamp.
     *
     * @return creation timestamp
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the session creation timestamp.
     *
     * @param createdAt creation timestamp
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the session completion timestamp.
     *
     * @return completion timestamp
     */
    public Instant getCompletedAt() {
        return completedAt;
    }

    /**
     * Sets the session completion timestamp.
     *
     * @param completedAt completion timestamp
     */
    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

}
