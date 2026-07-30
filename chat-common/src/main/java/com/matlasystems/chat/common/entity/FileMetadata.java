package com.matlasystems.chat.common.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents metadata for a transferred file.
 * <p>
 * The actual file contents are stored on disk.
 * This class stores only metadata required to
 * identify and validate the uploaded file.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public class FileMetadata implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long fileId;

    private Long senderId;

    private Long receiverId;

    private Long messageId;

    private String fileName;

    private String originalFileName;

    private String filePath;

    private String contentType;

    private long fileSize;

    private String checksum;

    private Instant uploadedAt;

    /**
     * Default constructor.
     */
    public FileMetadata() {
    }

    /**
     * Private constructor used by the Builder.
     */
    private FileMetadata(Builder builder) {
        this.fileId = builder.fileId;
        this.senderId = builder.senderId;
        this.receiverId = builder.receiverId;
        this.messageId = builder.messageId;
        this.fileName = builder.fileName;
        this.originalFileName = builder.originalFileName;
        this.filePath = builder.filePath;
        this.contentType = builder.contentType;
        this.fileSize = builder.fileSize;
        this.checksum = builder.checksum;
        this.uploadedAt = builder.uploadedAt;
    }

    /**
     * Returns a new Builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link FileMetadata}.
     */
    public static final class Builder {

        private Long fileId;
        private Long senderId;
        private Long receiverId;
        private Long messageId;
        private String fileName;
        private String originalFileName;
        private String filePath;
        private String contentType;
        private long fileSize;
        private String checksum;
        private Instant uploadedAt;

        private Builder() {
        }

        public Builder fileId(Long fileId) { this.fileId = fileId; return this; }
        public Builder senderId(Long senderId) { this.senderId = senderId; return this; }
        public Builder receiverId(Long receiverId) { this.receiverId = receiverId; return this; }
        public Builder messageId(Long messageId) { this.messageId = messageId; return this; }
        public Builder fileName(String fileName) { this.fileName = fileName; return this; }
        public Builder originalFileName(String originalFileName) { this.originalFileName = originalFileName; return this; }
        public Builder filePath(String filePath) { this.filePath = filePath; return this; }
        public Builder contentType(String contentType) { this.contentType = contentType; return this; }
        public Builder fileSize(long fileSize) { this.fileSize = fileSize; return this; }
        public Builder checksum(String checksum) { this.checksum = checksum; return this; }
        public Builder uploadedAt(Instant uploadedAt) { this.uploadedAt = uploadedAt; return this; }

        public FileMetadata build() {
            if (uploadedAt == null) {
                uploadedAt = Instant.now();
            }
            return new FileMetadata(this);
        }
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Instant getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Instant uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    /**
     * Returns true if the file has a checksum.
     */
    public boolean hasChecksum() {
        return checksum != null && !checksum.isBlank();
    }

    /**
     * Returns true if the file has been associated
     * with a chat message.
     */
    public boolean isAttachedToMessage() {
        return messageId != null;
    }

    /**
     * Returns true if the file is larger than 10 MB.
     */
    public boolean isLargeFile() {
        return fileSize > (10L * 1024L * 1024L);
    }

    /**
     * Returns true if this file is an image.
     */
    public boolean isImage() {
        return contentType != null &&
                contentType.startsWith("image/");
    }

    /**
     * Returns true if this file is a video.
     */
    public boolean isVideo() {
        return contentType != null &&
                contentType.startsWith("video/");
    }

    /**
     * Returns true if this file is a PDF.
     */
    public boolean isPdf() {
        return "application/pdf".equals(contentType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileMetadata that)) return false;
        return Objects.equals(fileId, that.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId);
    }

    @Override
    public String toString() {
        return "FileMetadata{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }

}
