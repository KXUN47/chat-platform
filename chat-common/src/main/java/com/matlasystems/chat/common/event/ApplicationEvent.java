package com.matlasystems.chat.common.event;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/** Base class for timestamped domain events. */
public abstract class ApplicationEvent implements Serializable {
    @Serial private static final long serialVersionUID = 1L;
    private final UUID eventId;
    private final Instant occurredAt;
    private final EventType type;
    private final EventPriority priority;
    private final String source;
    protected ApplicationEvent(EventType type, EventPriority priority, String source) { this.eventId=UUID.randomUUID(); this.occurredAt=Instant.now(); this.type=type; this.priority=priority == null ? EventPriority.NORMAL : priority; this.source=source; }
    public UUID getEventId() { return eventId; } public Instant getOccurredAt() { return occurredAt; }
    public EventType getType() { return type; } public EventPriority getPriority() { return priority; } public String getSource() { return source; }
}
