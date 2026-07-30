package com.matlasystems.chat.common.event.user;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a user connection is established.
 */
public final class UserConnectedEvent
        extends ApplicationEvent {

    private final Long userId;

    private final String ipAddress;

    /**
     * Creates a new user connected event.
     *
     * @param userId unique user identifier
     * @param ipAddress user's IP address
     */
    public UserConnectedEvent(
            Long userId,
            String ipAddress) {

        super(
                EventType.USER_CONNECTED,
                EventPriority.NORMAL,
                "user");

        this.userId = userId;
        this.ipAddress = ipAddress;
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
     * Returns the user's IP address.
     *
     * @return IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

}
