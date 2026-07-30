package com.matlasystems.chat.common.dto.response;

import com.matlasystems.chat.common.dto.user.UserDTO;
import java.io.Serializable;
import java.time.Instant;

/** Successful authentication response payload. */
public final class LoginResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionId;
    private String token;
    private Instant expiresAt;
    private UserDTO user;
    public LoginResponse() { }
    public LoginResponse(String sessionId, String token, Instant expiresAt, UserDTO user) { this.sessionId = sessionId; this.token = token; this.expiresAt = expiresAt; this.user = user; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public UserDTO getUser() { return user; }
    public void setUser(UserDTO user) { this.user = user; }
}
