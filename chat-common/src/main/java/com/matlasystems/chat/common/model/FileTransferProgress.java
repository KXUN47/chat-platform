package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * Tracks the progress of a file transfer.
 * <p>
 * A file transfer may represent either an upload or a download. This class
 * maintains the number of bytes transferred, total file size, timestamps, and
 * completion state.
 */
public class FileTransferProgress implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID transferId;

    private long totalBytes;

    private long transferredBytes;

    private Instant startedAt;

    private Instant updatedAt;

    private boolean completed;

    /**
     * Creates an empty file transfer progress instance.
     * <p>
     * Required for serialization frameworks.
     */
    public FileTransferProgress() {
        // Default constructor.
    }

    /**
     * Creates a new file transfer progress.
     *
     * @param transferId unique transfer identifier
     * @param totalBytes total number of bytes to transfer
     */
    public FileTransferProgress(
            UUID transferId,
            long totalBytes) {

        this.transferId = transferId;
        this.totalBytes = Math.clamp(
                totalBytes,
                0L,
                Long.MAX_VALUE);

        this.transferredBytes = 0L;

        this.startedAt = Instant.now();
        this.updatedAt = startedAt;

        this.completed = false;
    }

    /**
     * Updates the total number of transferred bytes.
     *
     * @param bytes transferred bytes
     */
    public void updateTransferredBytes(long bytes) {

        transferredBytes = Math.clamp(
                bytes,
                0L,
                totalBytes);

        updatedAt = Instant.now();

        completed = transferredBytes == totalBytes;
    }

    /**
     * Adds additional transferred bytes.
     *
     * @param bytes additional transferred bytes
     */
    public void addTransferredBytes(long bytes) {

        updateTransferredBytes(
                transferredBytes
                        + Math.clamp(
                                bytes,
                                0L,
                                Long.MAX_VALUE));
    }

    /**
     * Returns the completion percentage.
     *
     * @return percentage completed
     */
    public double getPercentage() {

        if (totalBytes == 0L) {
            return 100.0;
        }

        return (transferredBytes * 100.0) / totalBytes;
    }

    /**
     * Returns the remaining bytes.
     *
     * @return remaining bytes
     */
    public long getRemainingBytes() {

        return Math.clamp(
                totalBytes - transferredBytes,
                0L,
                Long.MAX_VALUE);
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
     * Returns the total file size.
     *
     * @return total bytes
     */
    public long getTotalBytes() {
        return totalBytes;
    }

    /**
     * Sets the total file size.
     *
     * @param totalBytes total bytes
     */
    public void setTotalBytes(long totalBytes) {

        this.totalBytes = Math.clamp(
                totalBytes,
                0L,
                Long.MAX_VALUE);

        if (transferredBytes > this.totalBytes) {
            transferredBytes = this.totalBytes;
        }

        completed = transferredBytes == this.totalBytes;
    }

    /**
     * Returns the transferred bytes.
     *
     * @return transferred bytes
     */
    public long getTransferredBytes() {
        return transferredBytes;
    }

    /**
     * Sets the transferred bytes.
     *
     * @param transferredBytes transferred bytes
     */
    public void setTransferredBytes(long transferredBytes) {
        updateTransferredBytes(transferredBytes);
    }

    /**
     * Returns the transfer start timestamp.
     *
     * @return start timestamp
     */
    public Instant getStartedAt() {
        return startedAt;
    }

    /**
     * Sets the transfer start timestamp.
     *
     * @param startedAt start timestamp
     */
    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    /**
     * Returns the last update timestamp.
     *
     * @return update timestamp
     */
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the last update timestamp.
     *
     * @param updatedAt update timestamp
     */
    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Indicates whether the transfer has completed.
     *
     * @return {@code true} if completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Sets the completion state.
     *
     * @param completed completion state
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
