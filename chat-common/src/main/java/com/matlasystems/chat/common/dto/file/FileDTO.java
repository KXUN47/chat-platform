package com.matlasystems.chat.common.dto.file;

import java.io.Serializable;
import java.time.Instant;

/**
 * Data Transfer Object containing metadata for a stored or transferred file.
 */
public final class FileDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique file identifier.
     */
    private String id;

    /**
     * Identifier of the file owner.
     */
    private String ownerId;

    /**
     * Original file name.
     */
    private String fileName;

    /**
     * MIME content type.
     * Example: application/pdf, image/png
     */
    private String contentType;

    /**
     * File size in bytes.
     */
    private long sizeBytes;

    /**
     * File checksum (SHA-256, MD5, etc.).
     */
    private String checksum;

    /**
     * Current file status.
     * Examples: UPLOADING, AVAILABLE, DELETED
     */
    private String status;

    /**
     * Timestamp when the file metadata was created.
     */
    private Instant createdAt;

    /**
     * Default constructor.
     */
    public FileDTO() {
      //
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public void setSizeBytes(long sizeBytes) {
        this.sizeBytes = sizeBytes;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "id='" + id + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", sizeBytes=" + sizeBytes +
                ", checksum='" + checksum + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
