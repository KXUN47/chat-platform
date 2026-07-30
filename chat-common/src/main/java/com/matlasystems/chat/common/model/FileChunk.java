package com.matlasystems.chat.common.model;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a single chunk of a file transferred between the
 * client and server.
 *
 * <p>
 * This record is immutable. The underlying byte array is
 * defensively copied during construction and whenever accessed.
 * </p>
 *
 * @param fileId unique file identifier
 * @param chunkNumber current chunk number (1-based)
 * @param totalChunks total number of chunks
 * @param data chunk payload
 * @param checksum SHA-256 checksum of the chunk
 * @param size chunk size in bytes
 * @param createdAt creation timestamp
 */
public record FileChunk(

        UUID fileId,

        int chunkNumber,

        int totalChunks,

        byte[] data,

        String checksum,

        int size,

        Instant createdAt

) {

    /**
     * Validates the record and performs defensive copying.
     */
    public FileChunk {

        Objects.requireNonNull(fileId,
                "fileId must not be null");

        Objects.requireNonNull(data,
                "data must not be null");

        Objects.requireNonNull(checksum,
                "checksum must not be null");

        Objects.requireNonNull(createdAt,
                "createdAt must not be null");

        if (chunkNumber < 1) {
            throw new IllegalArgumentException(
                    "chunkNumber must be greater than zero");
        }

        if (totalChunks < 1) {
            throw new IllegalArgumentException(
                    "totalChunks must be greater than zero");
        }

        if (chunkNumber > totalChunks) {
            throw new IllegalArgumentException(
                    "chunkNumber cannot exceed totalChunks");
        }

        if (size < 0) {
            throw new IllegalArgumentException(
                    "size cannot be negative");
        }

        if (size != data.length) {
            throw new IllegalArgumentException(
                    "size must equal data length");
        }

        data = data.clone();
    }

    /**
     * Returns a defensive copy of the chunk data.
     *
     * @return copied chunk bytes
     */
    @Override
    public byte[] data() {
        return data.clone();
    }

    /**
     * Returns whether this is the final chunk.
     *
     * @return true if this is the last chunk
     */
    public boolean isLastChunk() {
        return chunkNumber == totalChunks;
    }

    /**
     * Returns the chunk size in bytes.
     *
     * @return size
     */
    public int sizeInBytes() {
        return size;
    }

    /**
     * Compares array contents rather than references.
     */
    @Override
    @SuppressWarnings("java:S6878")
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof FileChunk other)) {
            return false;
        }

        return chunkNumber == other.chunkNumber
                && totalChunks == other.totalChunks
                && size == other.size
                && Objects.equals(fileId, other.fileId)
                && Arrays.equals(data, other.data)
                && Objects.equals(checksum, other.checksum)
                && Objects.equals(createdAt, other.createdAt);
    }

    /**
     * Computes a hash code using array contents.
     */
    @Override
    public int hashCode() {

        int result = Objects.hash(
                fileId,
                chunkNumber,
                totalChunks,
                checksum,
                size,
                createdAt);

        result = 31 * result + Arrays.hashCode(data);

        return result;
    }

    /**
     * Returns a concise string representation.
     */
    @Override
    public String toString() {

        return "FileChunk[" +
                "fileId=" + fileId +
                ", chunkNumber=" + chunkNumber +
                ", totalChunks=" + totalChunks +
                ", dataLength=" + data.length +
                ", checksum='" + checksum + '\'' +
                ", size=" + size +
                ", createdAt=" + createdAt +
                ']';
    }

}
