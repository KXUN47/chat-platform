package com.matlasystems.chat.common.dto.file;

import java.io.Serializable;
import java.util.Arrays;

/** A binary chunk belonging to a file transfer. */
public final class FileChunkDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fileId;
    private int chunkIndex;
    private int totalChunks;
    private byte[] data;
    private boolean lastChunk;
    public FileChunkDTO() { this.data = new byte[0]; }
    public FileChunkDTO(String fileId, int chunkIndex, int totalChunks, byte[] data, boolean lastChunk) { this.fileId = fileId; this.chunkIndex = chunkIndex; this.totalChunks = totalChunks; setData(data); this.lastChunk = lastChunk; }
    public String getFileId() { return fileId; }
    public void setFileId(String fileId) { this.fileId = fileId; }
    public int getChunkIndex() { return chunkIndex; }
    public void setChunkIndex(int chunkIndex) { this.chunkIndex = chunkIndex; }
    public int getTotalChunks() { return totalChunks; }
    public void setTotalChunks(int totalChunks) { this.totalChunks = totalChunks; }
    public byte[] getData() { return data == null ? new byte[0] : Arrays.copyOf(data, data.length); }
    public void setData(byte[] data) { this.data = data == null ? new byte[0] : Arrays.copyOf(data, data.length); }
    public boolean isLastChunk() { return lastChunk; }
    public void setLastChunk(boolean lastChunk) { this.lastChunk = lastChunk; }
}
