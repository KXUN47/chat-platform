package com.matlasystems.chat.common.model;

import java.time.Instant;
import java.util.UUID;

/**
 * Represents a heartbeat packet.
 *
 * @param sessionId session identifier
 * @param sequenceNumber heartbeat sequence
 * @param timestamp heartbeat timestamp
 * @param latency latency in milliseconds
 */
public record Heartbeat(

        UUID sessionId,

        long sequenceNumber,

        Instant timestamp,

        long latency

) {

    public boolean hasLatency() {
        return latency >= 0;
    }

}
