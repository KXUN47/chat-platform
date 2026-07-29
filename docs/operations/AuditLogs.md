# AuditLogs.md

# Audit Logging Architecture

**Project:** MATLA Chat Platform  
**Module:** chat-database  
**Version:** 1.0  
**Author:** MATLA SYSTEMS DEVELOPMENT

---

# 1. Purpose

The Audit Logging subsystem records significant events occurring within the application.

Unlike application logs (SLF4J/Logback), audit logs are stored permanently in the database and provide a historical record of:

- User activity
- Security events
- Administrative actions
- System events
- File operations
- Authentication attempts

Audit logs provide:

- Accountability
- Traceability
- Security investigations
- Compliance
- Operational reporting

---

# 2. Objectives

The audit logging subsystem shall:

- Record all security-sensitive events
- Record all user actions
- Record administrative actions
- Support historical reporting
- Allow forensic investigations
- Prevent data tampering

---

# 3. Scope

Audit logs include:

- Login
- Logout
- Failed login
- Password changes
- User registration
- Message sending
- File upload
- File download
- User disconnect
- Server startup
- Server shutdown
- Configuration changes
- Administrative commands

---

# 4. Architecture

```
Application

        │

        ▼

Business Service

        │

        ▼

Audit Service

        │

        ▼

Audit Repository

        │

        ▼

PostgreSQL
```

---

# 5. Audit Flow

```
User Login

      │

      ▼

AuthenticationService

      │

      ▼

AuditService

      │

      ▼

AuditRepository

      │

      ▼

audit_logs Table
```

---

# 6. Audit Event Types

## Authentication

- LOGIN_SUCCESS
- LOGIN_FAILED
- LOGOUT
- SESSION_TIMEOUT
- PASSWORD_CHANGED
- ACCOUNT_LOCKED

---

## Messaging

- MESSAGE_SENT
- PRIVATE_MESSAGE_SENT
- MESSAGE_DELETED
- MESSAGE_EDITED

---

## File Operations

- FILE_UPLOAD
- FILE_DOWNLOAD
- FILE_DELETE

---

## Administration

- USER_CREATED
- USER_DISABLED
- USER_ENABLED
- USER_KICKED
- USER_BANNED

---

## Server

- SERVER_STARTED
- SERVER_STOPPED
- CONFIGURATION_CHANGED

---

# 7. Audit Log Table

Table:

```
audit_logs
```

Columns

| Column | Type | Description |
|----------|------|-------------|
| audit_id | BIGSERIAL | Primary Key |
| user_id | BIGINT | User performing action |
| event_type | VARCHAR(100) | Event |
| entity_name | VARCHAR(100) | Object affected |
| entity_id | BIGINT | Record identifier |
| ip_address | VARCHAR(50) | Client IP |
| description | TEXT | Detailed description |
| created_at | TIMESTAMP | Event timestamp |

---

# 8. Example Audit Record

| Field | Value |
|--------|-------|
| user_id | 15 |
| event_type | LOGIN_SUCCESS |
| entity_name | USER |
| entity_id | 15 |
| ip_address | 192.168.0.10 |
| description | User logged into system |
| created_at | 2026-07-29 14:10 |

---

# 9. Retention Policy

| Data | Retention |
|--------|-----------|
| Authentication | 2 Years |
| Messages | Permanent |
| Administrative Actions | Permanent |
| File Operations | 2 Years |
| Server Events | 1 Year |

---

# 10. Indexes

```
idx_audit_user

idx_audit_event

idx_audit_date
```

---

# 11. Java Components

```
AuditService

AuditRepository

AuditEntity

AuditMapper

AuditEventType

AuditDTO
```

---

# 12. Best Practices

- Never update audit records.
- Never delete audit records.
- Use append-only inserts.
- Timestamp every event.
- Record originating IP.
- Record authenticated user.
- Record affected entity.

---

# 13. Future Improvements

- Audit dashboards
- SIEM integration
- Kafka event streaming
- Elasticsearch indexing
- Immutable audit storage