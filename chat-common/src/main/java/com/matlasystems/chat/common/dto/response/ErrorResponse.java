package com.matlasystems.chat.common.dto.response;

import java.io.Serializable;
import java.time.Instant;

/** Safe error payload returned when a request cannot be completed. */
public final class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private String requestId;
    private Instant timestamp;
    public ErrorResponse() { }
    public ErrorResponse(String code, String message, String requestId, Instant timestamp) { this.code = code; this.message = message; this.requestId = requestId; this.timestamp = timestamp; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
