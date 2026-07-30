package com.matlasystems.chat.common.event.user;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted after a new user account has been successfully created.
 */
public final class UserRegisteredEvent
        extends ApplicationEvent {

    private final Long userId;

    private final String username;

    /**
     * Creates a new user registered event.
     *
     * @param userId unique user identifier
     * @param username registered username
     */
    public UserRegisteredEvent(
            Long userId,
            String username) {

        super(
                EventType.USER_REGISTERED,
                EventPriority.NORMAL,
                "user");

        this.userId = userId;
        this.username = username;
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
     * Returns the registered username.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

}
