package com.matlasystems.chat.common.dto.common;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Data Transfer Object representing the health status of the application.
 * Includes the overall service status, uptime, timestamp of the check,
 * and optional health information for individual components.
 */
public final class HealthCheckDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Overall application status.
     * Examples: UP, DOWN, DEGRADED
     */
    private String status;

    /**
     * Timestamp when the health check was performed.
     */
    private Instant checkedAt;

    /**
     * Application uptime in seconds.
     */
    private long uptimeSeconds;

    /**
     * Individual component health statuses.
     * Example:
     * {
     *     "database": "UP",
     *     "redis": "UP",
     *     "filesystem": "DOWN"
     * }
     */
    private Map<String, String> components;

    /**
     * Default constructor.
     */
    public HealthCheckDTO() {
        this.components = new LinkedHashMap<>();
    }

    /**
     * Full constructor.
     *
     * @param status overall application status
     * @param checkedAt timestamp of health check
     * @param uptimeSeconds application uptime
     * @param components individual component statuses
     */
    public HealthCheckDTO(
            String status,
            Instant checkedAt,
            long uptimeSeconds,
            Map<String, String> components) {

        this.status = status;
        this.checkedAt = checkedAt;
        this.uptimeSeconds = uptimeSeconds;
        setComponents(components);
    }

    /**
     * Returns the overall application status.
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the overall application status.
     *
     * @param status application status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the timestamp of the health check.
     *
     * @return timestamp
     */
    public Instant getCheckedAt() {
        return checkedAt;
    }

    /**
     * Sets the timestamp of the health check.
     *
     * @param checkedAt timestamp
     */
    public void setCheckedAt(Instant checkedAt) {
        this.checkedAt = checkedAt;
    }

    /**
     * Returns the application uptime in seconds.
     *
     * @return uptime in seconds
     */
    public long getUptimeSeconds() {
        return uptimeSeconds;
    }

    /**
     * Sets the application uptime.
     *
     * @param uptimeSeconds uptime in seconds
     */
    public void setUptimeSeconds(long uptimeSeconds) {
        this.uptimeSeconds = uptimeSeconds;
    }

    /**
     * Returns an immutable copy of the component status map.
     *
     * @return unmodifiable component map
     */
    public Map<String, String> getComponents() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(components));
    }

    /**
     * Sets the component status map.
     * A defensive copy is created to protect internal state.
     *
     * @param components component status map
     */
    public void setComponents(Map<String, String> components) {
        this.components = (components == null)
                ? new LinkedHashMap<>()
                : new LinkedHashMap<>(components);
    }

    @Override
    public String toString() {
        return "HealthCheckDTO{" +
                "status='" + status + '\'' +
                ", checkedAt=" + checkedAt +
                ", uptimeSeconds=" + uptimeSeconds +
                ", components=" + components +
                '}';
    }
}
