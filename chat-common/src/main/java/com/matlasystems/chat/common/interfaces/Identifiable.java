package com.matlasystems.chat.common.interfaces;

/**
 * Represents an object that has a unique identifier.
 *
 * <p>This interface is implemented by all domain objects
 * that expose a primary identifier.
 *
 * <p>Examples:
 * <ul>
 *     <li>User</li>
 *     <li>Message</li>
 *     <li>Session</li>
 *     <li>FileMetadata</li>
 * </ul>
 *
 * @param <T> the identifier type
 *
 * @author MATLA Systems
 * @since 1.0.0
 */
public interface Identifiable<T> {

    /**
     * Returns the unique identifier.
     *
     * @return object identifier
     */
    T getId();

}
