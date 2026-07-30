package com.matlasystems.chat.common.event.user;

import java.util.UUID;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted after a user has been successfully authenticated.
 */
public final class UserLoggedInEvent
        extends ApplicationEvent {

    private final Long userId;

    private final UUID sessionId;

    /**
     * Creates a new user logged in event.
     *
     * @param userId authenticated user identifier
     * @param sessionId active session identifier
     */
    public UserLoggedInEvent(
            Long userId,
            UUID sessionId) {

        super(
                EventType.USER_LOGGED_IN,
                EventPriority.HIGH,
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
     * Returns the active session identifier.
     *
     * @return session ID
     */
    public UUID getSessionId() {
        return sessionId;
    }

}
