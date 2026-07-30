package com.matlasystems.chat.common.model;

import java.time.Duration;
import java.time.Instant;

/**
 * Runtime statistics for the chat server.
 *
 * @param connectedUsers current connected users
 * @param activeSessions active sessions
 * @param totalMessages total processed messages
 * @param totalFiles total uploaded files
 * @param totalConnections total client connections
 * @param failedLogins failed login attempts
 * @param serverStartTime server startup time
 * @param uptime current uptime
 * @param usedMemoryBytes JVM used memory
 * @param maxMemoryBytes JVM maximum memory
 */
public record ServerStatistics(

        int connectedUsers,

        int activeSessions,

        long totalMessages,

        long totalFiles,

        long totalConnections,

        long failedLogins,

        Instant serverStartTime,

        Duration uptime,

        long usedMemoryBytes,

        long maxMemoryBytes

) {

    public double memoryUsagePercentage() {

        if (maxMemoryBytes == 0) {
            return 0.0;
        }

        return (usedMemoryBytes * 100.0) / maxMemoryBytes;
    }

}
