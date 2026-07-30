package com.matlasystems.chat.common.exception;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Safe contextual information that may accompany a {@link ChatException}.
 */
public final class ErrorDetails implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String requestId;
    private String command;
    private String resource;
    private Instant timestamp;
    private Map<String, String> context = new LinkedHashMap<>();

    public ErrorDetails() {
    }

    private ErrorDetails(Builder builder) {
        this.requestId = builder.requestId;
        this.command = builder.command;
        this.resource = builder.resource;
        this.timestamp = builder.timestamp;
        this.context = new LinkedHashMap<>(builder.context);
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private String requestId;
        private String command;
        private String resource;
        private Instant timestamp;
        private final Map<String, String> context = new LinkedHashMap<>();

        private Builder() { }

        public Builder requestId(String requestId) { this.requestId = requestId; return this; }
        public Builder command(String command) { this.command = command; return this; }
        public Builder resource(String resource) { this.resource = resource; return this; }
        public Builder timestamp(Instant timestamp) { this.timestamp = timestamp; return this; }
        public Builder context(String key, String value) { this.context.put(key, value); return this; }
        public Builder context(Map<String, String> context) {
            this.context.clear();
            if (context != null) { this.context.putAll(context); }
            return this;
        }
        public ErrorDetails build() {
            if (timestamp == null) { timestamp = Instant.now(); }
            return new ErrorDetails(this);
        }
    }

    public String getRequestId() { return requestId; }
    public void setRequestId(String requestId) { this.requestId = requestId; }
    public String getCommand() { return command; }
    public void setCommand(String command) { this.command = command; }
    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public Map<String, String> getContext() { return Collections.unmodifiableMap(context); }
    public void setContext(Map<String, String> context) {
        this.context = context == null ? new LinkedHashMap<>() : new LinkedHashMap<>(context);
    }
}
