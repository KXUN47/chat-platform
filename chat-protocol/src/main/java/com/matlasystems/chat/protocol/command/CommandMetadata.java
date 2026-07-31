package com.matlasystems.chat.protocol.command;

import java.util.Objects;

import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Metadata describing a protocol command.
 *
 * This class contains documentation and operational information
 * about a command but does not execute the command.
 */
public final class CommandMetadata {

    /**
     * Human readable name.
     */
    private final String displayName;

    /**
     * Description of the command.
     */
    private final String description;

    /**
     * Maximum allowed payload size in bytes.
     */
    private final int maxPayloadSize;

    /**
     * Timeout before request expires.
     */
    private final long timeoutMillis;

    /**
     * Whether the command is deprecated.
     */
    private final boolean deprecated;

    /**
     * Replacement command if deprecated.
     */
    private final String replacementCommand;

    /**
     * Version when command first became available.
     */
    private final ProtocolVersion sinceVersion;

    /**
     * Creates command metadata.
     */
    public CommandMetadata(
            String displayName,
            String description,
            int maxPayloadSize,
            long timeoutMillis,
            boolean deprecated,
            String replacementCommand,
            ProtocolVersion sinceVersion) {

        this.displayName = Objects.requireNonNull(displayName);
        this.description = Objects.requireNonNull(description);
        this.maxPayloadSize = maxPayloadSize;
        this.timeoutMillis = timeoutMillis;
        this.deprecated = deprecated;
        this.replacementCommand = replacementCommand;
        this.sinceVersion = Objects.requireNonNull(sinceVersion);
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxPayloadSize() {
        return maxPayloadSize;
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public String getReplacementCommand() {
        return replacementCommand;
    }

    public ProtocolVersion getSinceVersion() {
        return sinceVersion;
    }

    @Override
    public String toString() {
        return "CommandMetadata{" +
                "displayName='" + displayName + '\'' +
                ", description='" + description + '\'' +
                ", maxPayloadSize=" + maxPayloadSize +
                ", timeoutMillis=" + timeoutMillis +
                ", deprecated=" + deprecated +
                ", replacementCommand='" + replacementCommand + '\'' +
                ", sinceVersion=" + sinceVersion +
                '}';
    }
}
