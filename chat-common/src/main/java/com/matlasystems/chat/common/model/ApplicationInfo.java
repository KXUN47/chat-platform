package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

/** Describes a running application build. */
public class ApplicationInfo implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private String name;
    private String version;
    private String buildVersion;
    private Instant startedAt;
    private String environment;
    public ApplicationInfo() { }
    public ApplicationInfo(String name, String version, String buildVersion, Instant startedAt, String environment) { this.name=name; this.version=version; this.buildVersion=buildVersion; this.startedAt=startedAt; this.environment=environment; }
    public String getName() { return name; } public void setName(String name) { this.name=name; }
    public String getVersion() { return version; } public void setVersion(String version) { this.version=version; }
    public String getBuildVersion() { return buildVersion; } public void setBuildVersion(String buildVersion) { this.buildVersion=buildVersion; }
    public Instant getStartedAt() { return startedAt; } public void setStartedAt(Instant startedAt) { this.startedAt=startedAt; }
    public String getEnvironment() { return environment; } public void setEnvironment(String environment) { this.environment=environment; }
}
