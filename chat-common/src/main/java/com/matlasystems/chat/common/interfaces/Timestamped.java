package com.matlasystems.chat.common.interfaces;

import java.time.Instant;

/**
 * Represents an object that stores creation
 * and last modification timestamps.
 *
 * Implementing this interface promotes
 * consistency across domain entities.
 *
 * @author MATLA Systems
 * @since 1.0.0
 */
public interface Timestamped {

    /**
     * Returns the creation timestamp.
     *
     * @return creation time
     */
    Instant getCreatedAt();

    /**
     * Returns the last modification timestamp.
     *
     * @return update time
     */
    Instant getUpdatedAt();

}
