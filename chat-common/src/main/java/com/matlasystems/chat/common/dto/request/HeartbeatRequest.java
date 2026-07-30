package com.matlasystems.chat.common.dto.request;

import java.io.Serializable;
import java.time.Instant;

/** Heartbeat payload sent by a connected client. */
public final class HeartbeatRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionId;
    private Instant sentAt;
    public HeartbeatRequest() { }
    public HeartbeatRequest(String sessionId, Instant sentAt) { this.sessionId = sessionId; this.sentAt = sentAt; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public Instant getSentAt() { return sentAt; }
    public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
}
