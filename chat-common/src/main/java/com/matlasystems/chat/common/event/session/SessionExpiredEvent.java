package com.matlasystems.chat.common.event.session;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when an authenticated session expires.
 */
public final class SessionExpiredEvent
        extends ApplicationEvent {

    private final UUID sessionId;

    private final Long userId;

    /**
     * Creates a new session expired event.
     *
     * @param sessionId unique session identifier
     * @param userId authenticated user identifier
     */
    public SessionExpiredEvent(
            UUID sessionId,
            Long userId) {

        super(
                EventType.SESSION_EXPIRED,
                EventPriority.HIGH,
                "session");

        this.sessionId = sessionId;
        this.userId = userId;
    }

    /**
     * Returns the session identifier.
     *
     * @return session ID
     */
    public UUID getSessionId() {
        return sessionId;
    }

    /**
     * Returns the authenticated user identifier.
     *
     * @return user ID
     */
    public Long getUserId() {
        return userId;
    }

}
