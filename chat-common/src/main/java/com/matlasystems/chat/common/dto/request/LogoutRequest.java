package com.matlasystems.chat.common.dto.request;

import java.io.Serializable;

/** Request payload for ending an authenticated session. */
public final class LogoutRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionId;
    public LogoutRequest() { }
    public LogoutRequest(String sessionId) { this.sessionId = sessionId; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
}
