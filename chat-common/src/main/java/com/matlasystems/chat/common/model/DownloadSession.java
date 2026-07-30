package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import com.matlasystems.chat.common.entity.FileMetadata;

/**
 * Represents the state of an active file download session.
 * <p>
 * A download session tracks:
 * <ul>
 *     <li>The unique transfer identifier</li>
 *     <li>The downloading user</li>
 *     <li>The file being downloaded</li>
 *     <li>The current transfer progress</li>
 *     <li>The session creation timestamp</li>
 *     <li>The session completion timestamp</li>
 * </ul>
 */
public class DownloadSession implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID transferId;

    private Long downloaderId;

    private FileMetadata fileMetadata;

    private FileTransferProgress progress;

    private Instant createdAt;

    private Instant completedAt;

    /**
     * Creates an empty download session.
     */
    public DownloadSession() {
        // Required for serialization frameworks.
    }

    /**
     * Creates a new download session.
     *
     * @param downloaderId downloading user identifier
     * @param fileMetadata metadata describing the file
     */
    public DownloadSession(
            Long downloaderId,
            FileMetadata fileMetadata) {

        this.transferId = UUID.randomUUID();
        this.downloaderId = downloaderId;
        this.fileMetadata = fileMetadata;
        this.progress = new FileTransferProgress(
                transferId,
                fileMetadata == null
                        ? 0L
                        : fileMetadata.getFileSize());
        this.createdAt = Instant.now();
    }

    /**
     * Marks the download as completed.
     */
    public void complete() {

        if (progress != null) {
            progress.updateTransferredBytes(
                    progress.getTotalBytes());
        }

        completedAt = Instant.now();
    }

    /**
     * Determines whether the download has completed.
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
     * @return transfer ID
     */
    public UUID getTransferId() {
        return transferId;
    }

    /**
     * Sets the transfer identifier.
     *
     * @param transferId transfer ID
     */
    public void setTransferId(UUID transferId) {
        this.transferId = transferId;
    }

    /**
     * Returns the downloader identifier.
     *
     * @return downloader ID
     */
    public Long getDownloaderId() {
        return downloaderId;
    }

    /**
     * Sets the downloader identifier.
     *
     * @param downloaderId downloader ID
     */
    public void setDownloaderId(Long downloaderId) {
        this.downloaderId = downloaderId;
    }

    /**
     * Returns the file metadata.
     *
     * @return file metadata
     */
    public FileMetadata getFileMetadata() {
        return fileMetadata;
    }

    /**
     * Sets the file metadata.
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
     * Returns the creation timestamp.
     *
     * @return creation time
     */
    public Instant getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param createdAt creation time
     */
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the completion timestamp.
     *
     * @return completion time
     */
    public Instant getCompletedAt() {
        return completedAt;
    }

    /**
     * Sets the completion timestamp.
     *
     * @param completedAt completion time
     */
    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

}
