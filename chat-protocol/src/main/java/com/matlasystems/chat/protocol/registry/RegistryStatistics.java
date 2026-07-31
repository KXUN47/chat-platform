package com.matlasystems.chat.protocol.registry;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents runtime statistics for the protocol registries.
 *
 * This class is immutable and thread-safe.
 *
 * Example:
 *
 * Registry Statistics
 * -------------------
 * Commands      : 18
 * Handlers      : 18
 * Parsers       : 7
 * Validators    : 5
 * Serializers   : 4
 * Versions      : 2
 */
public final class RegistryStatistics implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final int commandCount;

    private final int handlerCount;

    private final int parserCount;

    private final int serializerCount;

    private final int validatorCount;

    private final int versionCount;

    private final Instant generatedAt;

    /**
     * Creates registry statistics.
     */
    public RegistryStatistics(
            int commandCount,
            int handlerCount,
            int parserCount,
            int serializerCount,
            int validatorCount,
            int versionCount) {

        this.commandCount = commandCount;
        this.handlerCount = handlerCount;
        this.parserCount = parserCount;
        this.serializerCount = serializerCount;
        this.validatorCount = validatorCount;
        this.versionCount = versionCount;
        this.generatedAt = Instant.now();

    }

    public int getCommandCount() {
        return commandCount;
    }

    public int getHandlerCount() {
        return handlerCount;
    }

    public int getParserCount() {
        return parserCount;
    }

    public int getSerializerCount() {
        return serializerCount;
    }

    public int getValidatorCount() {
        return validatorCount;
    }

    public int getVersionCount() {
        return versionCount;
    }

    /**
     * Returns the timestamp when the statistics were generated.
     */
    public Instant getGeneratedAt() {
        return generatedAt;
    }

    /**
     * Returns the total number of registered components.
     */
    public int getTotalComponents() {

        return commandCount
                + handlerCount
                + parserCount
                + serializerCount
                + validatorCount
                + versionCount;

    }

    /**
     * Indicates whether every registry contains at least one component.
     */
    public boolean isComplete() {

        return commandCount > 0
                && handlerCount > 0
                && parserCount > 0
                && serializerCount > 0
                && validatorCount > 0
                && versionCount > 0;

    }

    @Override
    public String toString() {

        return """
                Registry Statistics
                -------------------
                Commands      : %d
                Handlers      : %d
                Parsers       : %d
                Serializers   : %d
                Validators    : %d
                Versions      : %d
                Total         : %d
                Generated At  : %s
                """.formatted(
                commandCount,
                handlerCount,
                parserCount,
                serializerCount,
                validatorCount,
                versionCount,
                getTotalComponents(),
                generatedAt
        );

    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof RegistryStatistics other)) {
            return false;
        }

        return commandCount == other.commandCount
                && handlerCount == other.handlerCount
                && parserCount == other.parserCount
                && serializerCount == other.serializerCount
                && validatorCount == other.validatorCount
                && versionCount == other.versionCount;

    }

    @Override
    public int hashCode() {

        return Objects.hash(
                commandCount,
                handlerCount,
                parserCount,
                serializerCount,
                validatorCount,
                versionCount
        );

    }

}
