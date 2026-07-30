package com.matlasystems.chat.common.interfaces;

/**
 * Represents a component capable of reporting
 * its operational health.
 */
public interface HealthCheck {

    /**
     * Returns true if the component is healthy.
     *
     * @return true if healthy
     */
    boolean isHealthy();

    /**
     * Returns a human-readable health description.
     *
     * @return status message
     */
    String getHealthStatus();

}
