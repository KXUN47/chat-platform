package com.matlasystems.chat.common.dto.user;

import java.io.Serializable;
import java.time.Instant;

/** Presence information for a user. */
public final class UserStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String status;
    private Instant lastSeenAt;
    public UserStatusDTO() { }
    public UserStatusDTO(String userId, String status, Instant lastSeenAt) { this.userId = userId; this.status = status; this.lastSeenAt = lastSeenAt; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Instant getLastSeenAt() { return lastSeenAt; }
    public void setLastSeenAt(Instant lastSeenAt) { this.lastSeenAt = lastSeenAt; }
}
