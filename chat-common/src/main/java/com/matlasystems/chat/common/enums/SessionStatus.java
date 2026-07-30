package com.matlasystems.chat.common.enums;

/**
 * Represents the lifecycle state of a user session.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum SessionStatus {

    /**
     * Session is active.
     */
    ACTIVE,

    /**
     * Session has no recent activity.
     */
    IDLE,

    /**
     * Session expired due to inactivity.
     */
    EXPIRED,

    /**
     * Session closed normally.
     */
    CLOSED;

    /**
     * Determines whether the session
     * can still be used.
     *
     * @return true if active
     */
    public boolean isActive() {
        return this == ACTIVE;
    }

    /**
     * Determines whether the session
     * has permanently ended.
     *
     * @return true if closed
     */
    public boolean isClosed() {
        return this == CLOSED;
    }

    /**
     * Determines whether the session
     * has expired.
     *
     * @return true if expired
     */
    public boolean isExpired() {
        return this == EXPIRED;
    }

}
