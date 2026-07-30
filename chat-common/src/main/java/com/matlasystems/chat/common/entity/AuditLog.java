package com.matlasystems.chat.common.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * Represents a significant user or system event retained for auditing.
 */
public class AuditLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long auditId;
    private Long userId;
    private String eventType;
    private String entityName;
    private Long entityId;
    private String ipAddress;
    private String description;
    private Instant createdAt;

    public AuditLog() {
    }

    private AuditLog(Builder builder) {
        this.auditId = builder.auditId;
        this.userId = builder.userId;
        this.eventType = builder.eventType;
        this.entityName = builder.entityName;
        this.entityId = builder.entityId;
        this.ipAddress = builder.ipAddress;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long auditId;
        private Long userId;
        private String eventType;
        private String entityName;
        private Long entityId;
        private String ipAddress;
        private String description;
        private Instant createdAt;

        private Builder() {
        }

        public Builder auditId(Long auditId) { this.auditId = auditId; return this; }
        public Builder userId(Long userId) { this.userId = userId; return this; }
        public Builder eventType(String eventType) { this.eventType = eventType; return this; }
        public Builder entityName(String entityName) { this.entityName = entityName; return this; }
        public Builder entityId(Long entityId) { this.entityId = entityId; return this; }
        public Builder ipAddress(String ipAddress) { this.ipAddress = ipAddress; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder createdAt(Instant createdAt) { this.createdAt = createdAt; return this; }

        public AuditLog build() {
            if (createdAt == null) {
                createdAt = Instant.now();
            }
            return new AuditLog(this);
        }
    }

    public Long getAuditId() { return auditId; }
    public void setAuditId(Long auditId) { this.auditId = auditId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object object) {
        return this == object || (object instanceof AuditLog other
                && Objects.equals(auditId, other.auditId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(auditId);
    }

    @Override
    public String toString() {
        return "AuditLog{" + "auditId=" + auditId + ", userId=" + userId
                + ", eventType='" + eventType + '\'' + ", createdAt=" + createdAt + '}';
    }
}
