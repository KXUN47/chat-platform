package com.matlasystems.chat.common.interfaces;

/**
 * Represents a component that must be initialized
 * before it can be used.
 *
 * Typical implementations include:
 * <ul>
 *     <li>Socket servers</li>
 *     <li>Database connection pools</li>
 *     <li>Configuration managers</li>
 *     <li>Protocol registries</li>
 * </ul>
 *
 * @author MATLA Systems
 * @since 1.0.0
 */
public interface Initializable {

    /**
     * Initializes the component.
     *
     * Implementations should allocate required
     * resources and prepare the component for use.
     *
     * @throws IllegalStateException
     *         if initialization cannot be completed
     */
    void initialize();

}
