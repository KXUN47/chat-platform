package com.matlasystems.chat.common.event.user;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a user logs out of the application.
 */
public final class UserLoggedOutEvent
        extends ApplicationEvent {

    private final Long userId;

    private final UUID sessionId;

    /**
     * Creates a new user logged out event.
     *
     * @param userId authenticated user identifier
     * @param sessionId session identifier being terminated
     */
    public UserLoggedOutEvent(
            Long userId,
            UUID sessionId) {

        super(
                EventType.USER_LOGGED_OUT,
                EventPriority.NORMAL,
                "authentication");

        this.userId = userId;
        this.sessionId = sessionId;
    }

    /**
     * Returns the authenticated user identifier.
     *
     * @return user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the terminated session identifier.
     *
     * @return session ID
     */
    public UUID getSessionId() {
        return sessionId;
    }

}
