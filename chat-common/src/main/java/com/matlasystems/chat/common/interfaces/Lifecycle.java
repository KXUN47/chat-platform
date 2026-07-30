package com.matlasystems.chat.common.interfaces;

/**
 * Represents a component that has a managed lifecycle.
 */
public interface Lifecycle {

    /**
     * Starts the component.
     */
    void start();

    /**
     * Stops the component gracefully.
     */
    void stop();

    /**
     * Returns true if the component is running.
     *
     * @return running status
     */
    boolean isRunning();

}
