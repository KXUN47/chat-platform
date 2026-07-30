package com.matlasystems.chat.common.dto.session;

import java.io.Serializable;
import java.time.Instant;

/** Authenticated-session metadata. Tokens and passwords are intentionally excluded. */
public final class SessionDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private Instant createdAt;
    private Instant expiresAt;
    private Instant lastAccessedAt;
    private String remoteAddress;
    public SessionDTO() { }
    public SessionDTO(String id, String userId, Instant createdAt, Instant expiresAt, Instant lastAccessedAt, String remoteAddress) { this.id = id; this.userId = userId; this.createdAt = createdAt; this.expiresAt = expiresAt; this.lastAccessedAt = lastAccessedAt; this.remoteAddress = remoteAddress; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public Instant getLastAccessedAt() { return lastAccessedAt; }
    public void setLastAccessedAt(Instant lastAccessedAt) { this.lastAccessedAt = lastAccessedAt; }
    public String getRemoteAddress() { return remoteAddress; }
    public void setRemoteAddress(String remoteAddress) { this.remoteAddress = remoteAddress; }
}
