package com.matlasystems.chat.common.entity;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import com.matlasystems.chat.common.enums.SessionStatus;

/**
 * Represents a logged-in user session.
 */
public class Session {

    private UUID sessionId;

    private Long userId;

    private Instant loginTime;

    private Instant lastActivity;

    private String ipAddress;

    private Integer port;

    private SessionStatus status;

    public Session() {
    }

    public Session(UUID sessionId,
                   Long userId,
                   Instant loginTime,
                   Instant lastActivity,
                   String ipAddress,
                   Integer port,
                   SessionStatus status) {

        this.sessionId = sessionId;
        this.userId = userId;
        this.loginTime = loginTime;
        this.lastActivity = lastActivity;
        this.ipAddress = ipAddress;
        this.port = port;
        this.status = status;
    }

    public void touch() {
        this.lastActivity = Instant.now();
    }

    public boolean isActive() {
        return status == SessionStatus.ACTIVE;
    }

    public boolean isExpired(Duration timeout) {

        return lastActivity.plus(timeout)
                .isBefore(Instant.now());
    }

    public void logout() {
        this.status = SessionStatus.CLOSED;
        this.lastActivity = Instant.now();
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Instant getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Instant loginTime) {
        this.loginTime = loginTime;
    }

    public Instant getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Instant lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (!(o instanceof Session))
            return false;

        Session session = (Session) o;

        return Objects.equals(sessionId, session.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionId);
    }

    @Override
    public String toString() {

        return "Session{" +
                "sessionId=" + sessionId +
                ", userId=" + userId +
                ", status=" + status +
                ", loginTime=" + loginTime +
                '}';
    }

}
