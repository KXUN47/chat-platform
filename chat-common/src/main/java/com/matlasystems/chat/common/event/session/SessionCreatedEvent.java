package com.matlasystems.chat.common.event.session;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when an authenticated session is created.
 */
public final class SessionCreatedEvent
        extends ApplicationEvent {

    private final UUID sessionId;

    private final Long userId;

    /**
     * Creates a new session created event.
     *
     * @param sessionId unique session identifier
     * @param userId authenticated user identifier
     */
    public SessionCreatedEvent(
            UUID sessionId,
            Long userId) {

        super(
                EventType.SESSION_CREATED,
                EventPriority.NORMAL,
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
