package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Represents operating system information suitable for health,
 * monitoring, and diagnostics endpoints.
 */
public class SystemInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String operatingSystem;

    private String architecture;

    private String hostname;

    private int availableProcessors;

    /**
     * Creates an empty system information instance.
     */
    public SystemInfo() {
        // Required for serialization frameworks.
    }

    /**
     * Captures the current system information.
     *
     * @return populated system information
     */
    public static SystemInfo capture() {

        SystemInfo systemInfo = new SystemInfo();

        systemInfo.operatingSystem =
                System.getProperty("os.name");

        systemInfo.architecture =
                System.getProperty("os.arch");

        systemInfo.availableProcessors =
                Runtime.getRuntime().availableProcessors();

        try {
            systemInfo.hostname =
                    InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ignored) {
            systemInfo.hostname = "unknown";
        }

        return systemInfo;
    }

    /**
     * Returns the operating system name.
     *
     * @return operating system
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * Sets the operating system name.
     *
     * @param operatingSystem operating system
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * Returns the CPU architecture.
     *
     * @return CPU architecture
     */
    public String getArchitecture() {
        return architecture;
    }

    /**
     * Sets the CPU architecture.
     *
     * @param architecture CPU architecture
     */
    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    /**
     * Returns the hostname.
     *
     * @return hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * Sets the hostname.
     *
     * @param hostname hostname
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Returns the number of available processors.
     *
     * @return processor count
     */
    public int getAvailableProcessors() {
        return availableProcessors;
    }

    /**
     * Sets the number of available processors.
     *
     * @param availableProcessors processor count
     */
    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

}
