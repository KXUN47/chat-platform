package com.matlasystems.chat.common.dto.user;

import java.io.Serializable;
import java.time.Instant;

/** Public user profile data safe to transfer across the protocol. */
public final class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String username;
    private String displayName;
    private String email;
    private UserStatusDTO status;
    private Instant createdAt;
    private Instant lastSeenAt;
    public UserDTO() { }
    public UserDTO(String id, String username, String displayName, String email, UserStatusDTO status, Instant createdAt, Instant lastSeenAt) { this.id = id; this.username = username; this.displayName = displayName; this.email = email; this.status = status; this.createdAt = createdAt; this.lastSeenAt = lastSeenAt; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public UserStatusDTO getStatus() { return status; }
    public void setStatus(UserStatusDTO status) { this.status = status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getLastSeenAt() { return lastSeenAt; }
    public void setLastSeenAt(Instant lastSeenAt) { this.lastSeenAt = lastSeenAt; }
}
