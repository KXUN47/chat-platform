package com.matlasystems.chat.common.event.user;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a user connection is closed.
 */
public final class UserDisconnectedEvent
        extends ApplicationEvent {

    private final Long userId;

    private final String reason;

    /**
     * Creates a new user disconnected event.
     *
     * @param userId unique user identifier
     * @param reason reason for the disconnection
     */
    public UserDisconnectedEvent(
            Long userId,
            String reason) {

        super(
                EventType.USER_DISCONNECTED,
                EventPriority.NORMAL,
                "user");

        this.userId = userId;
        this.reason = reason;
    }

    /**
     * Returns the user identifier.
     *
     * @return user ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Returns the reason for the disconnection.
     *
     * @return disconnection reason
     */
    public String getReason() {
        return reason;
    }

}
