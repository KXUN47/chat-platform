package com.matlasystems.chat.common.dto.response;

import java.io.Serializable;
import java.time.Instant;

/** Generic success payload for operations without a specialized response. */
public final class SuccessResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String message;
    private String requestId;
    private Instant timestamp;
    public SuccessResponse() { }
    public SuccessResponse(String message, String requestId, Instant timestamp) { this.message = message; this.requestId = requestId; this.timestamp = timestamp; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
