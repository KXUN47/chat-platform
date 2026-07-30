package com.matlasystems.chat.common.dto.server;

import java.io.Serializable;
import java.time.Instant;

/** Non-sensitive server information exposed to clients and operators. */
public final class ServerInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String version;
    private String environment;
    private Instant startedAt;
    private long uptimeSeconds;
    private int activeConnections;
    public ServerInfoDTO() { }
    public ServerInfoDTO(String name, String version, String environment, Instant startedAt, long uptimeSeconds, int activeConnections) { this.name = name; this.version = version; this.environment = environment; this.startedAt = startedAt; this.uptimeSeconds = uptimeSeconds; this.activeConnections = activeConnections; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public Instant getStartedAt() { return startedAt; }
    public void setStartedAt(Instant startedAt) { this.startedAt = startedAt; }
    public long getUptimeSeconds() { return uptimeSeconds; }
    public void setUptimeSeconds(long uptimeSeconds) { this.uptimeSeconds = uptimeSeconds; }
    public int getActiveConnections() { return activeConnections; }
    public void setActiveConnections(int activeConnections) { this.activeConnections = activeConnections; }
}
