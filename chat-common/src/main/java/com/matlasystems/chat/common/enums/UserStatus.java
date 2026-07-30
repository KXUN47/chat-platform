package com.matlasystems.chat.common.enums;

/**
 * Represents the availability status of a user.
 *
 * This status indicates whether a user is currently
 * available to receive messages or interact with
 * other users.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum UserStatus {

    /**
     * User is currently connected and available.
     */
    ONLINE,

    /**
     * User is disconnected.
     */
    OFFLINE,

    /**
     * User is connected but temporarily away.
     */
    AWAY,

    /**
     * User is connected but does not want interruptions.
     */
    BUSY,

    /**
     * User is connected but hidden from other users.
     */
    INVISIBLE;

    /**
     * Determines whether the user is available
     * to receive normal chat messages.
     *
     * @return true if available
     */
    public boolean isAvailable() {
        return this == ONLINE || this == AWAY;
    }

    /**
     * Determines whether the user is currently online.
     *
     * @return true if online
     */
    public boolean isOnline() {
        return this != OFFLINE;
    }

    /**
     * Determines whether the user is currently offline.
     *
     * @return true if offline
     */
    public boolean isOffline() {
        return this == OFFLINE;
    }

}
