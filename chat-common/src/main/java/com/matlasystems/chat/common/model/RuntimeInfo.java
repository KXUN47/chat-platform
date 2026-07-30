package com.matlasystems.chat.common.model;

import java.io.Serial;
import java.io.Serializable;
import java.lang.management.ManagementFactory;

/**
 * Represents a snapshot of the current Java Virtual Machine runtime
 * information.
 */
public class RuntimeInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String javaVersion;

    private String vmName;

    private long uptimeMillis;

    private int availableProcessors;

    private long maxMemoryBytes;

    private long totalMemoryBytes;

    private long freeMemoryBytes;

    /**
     * Creates an empty runtime information instance.
     */
    public RuntimeInfo() {
        // Required for serialization frameworks.
    }

    /**
     * Captures the current JVM runtime information.
     *
     * @return populated runtime information
     */
    public static RuntimeInfo capture() {

        Runtime runtime = Runtime.getRuntime();

        RuntimeInfo runtimeInfo = new RuntimeInfo();

        runtimeInfo.javaVersion = System.getProperty("java.version");
        runtimeInfo.vmName = System.getProperty("java.vm.name");
        runtimeInfo.uptimeMillis =
                ManagementFactory.getRuntimeMXBean().getUptime();

        runtimeInfo.availableProcessors =
                runtime.availableProcessors();

        runtimeInfo.maxMemoryBytes =
                runtime.maxMemory();

        runtimeInfo.totalMemoryBytes =
                runtime.totalMemory();

        runtimeInfo.freeMemoryBytes =
                runtime.freeMemory();

        return runtimeInfo;
    }

    /**
     * Returns the Java version.
     *
     * @return Java version
     */
    public String getJavaVersion() {
        return javaVersion;
    }

    /**
     * Sets the Java version.
     *
     * @param javaVersion Java version
     */
    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    /**
     * Returns the JVM name.
     *
     * @return JVM name
     */
    public String getVmName() {
        return vmName;
    }

    /**
     * Sets the JVM name.
     *
     * @param vmName JVM name
     */
    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    /**
     * Returns the JVM uptime.
     *
     * @return uptime in milliseconds
     */
    public long getUptimeMillis() {
        return uptimeMillis;
    }

    /**
     * Sets the JVM uptime.
     *
     * @param uptimeMillis uptime in milliseconds
     */
    public void setUptimeMillis(long uptimeMillis) {
        this.uptimeMillis = uptimeMillis;
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
     * Sets the processor count.
     *
     * @param availableProcessors processor count
     */
    public void setAvailableProcessors(int availableProcessors) {
        this.availableProcessors = availableProcessors;
    }

    /**
     * Returns the maximum JVM memory.
     *
     * @return maximum memory in bytes
     */
    public long getMaxMemoryBytes() {
        return maxMemoryBytes;
    }

    /**
     * Sets the maximum JVM memory.
     *
     * @param maxMemoryBytes maximum memory in bytes
     */
    public void setMaxMemoryBytes(long maxMemoryBytes) {
        this.maxMemoryBytes = maxMemoryBytes;
    }

    /**
     * Returns the allocated JVM memory.
     *
     * @return allocated memory in bytes
     */
    public long getTotalMemoryBytes() {
        return totalMemoryBytes;
    }

    /**
     * Sets the allocated JVM memory.
     *
     * @param totalMemoryBytes allocated memory in bytes
     */
    public void setTotalMemoryBytes(long totalMemoryBytes) {
        this.totalMemoryBytes = totalMemoryBytes;
    }

    /**
     * Returns the free JVM memory.
     *
     * @return free memory in bytes
     */
    public long getFreeMemoryBytes() {
        return freeMemoryBytes;
    }

    /**
     * Sets the free JVM memory.
     *
     * @param freeMemoryBytes free memory in bytes
     */
    public void setFreeMemoryBytes(long freeMemoryBytes) {
        this.freeMemoryBytes = freeMemoryBytes;
    }

    /**
     * Returns the used JVM memory.
     *
     * @return used memory in bytes
     */
    public long getUsedMemoryBytes() {
        return totalMemoryBytes - freeMemoryBytes;
    }

}
