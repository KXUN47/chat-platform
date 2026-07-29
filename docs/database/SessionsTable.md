# SessionsTable.md

# Sessions Table Design

## Purpose

The `sessions` table tracks every authenticated connection between a user and the chat server.

Each login creates a new session.

---

# Business Rules

- A user can have multiple sessions.
- Sessions expire after inactivity.
- Sessions are invalidated on logout.
- Session IDs are globally unique.

---

# Table Name

```text
sessions
```

Primary Key

```text
session_id
```

---

# Columns

| Column | Type | Nullable | Description |
|----------|---------|----------|-------------|
| session_id | UUID | No | Primary Key |
| user_id | BIGINT | No | Owner |
| session_token | UUID | No | Authentication Token |
| client_ip | VARCHAR(50) | No | Client Address |
| client_host | VARCHAR(255) | Yes | Host Name |
| operating_system | VARCHAR(100) | Yes | Client OS |
| application_version | VARCHAR(50) | Yes | Client Version |
| login_time | TIMESTAMP | No | Login Timestamp |
| last_activity | TIMESTAMP | No | Last Request |
| logout_time | TIMESTAMP | Yes | Logout Timestamp |
| expires_at | TIMESTAMP | No | Expiry |
| status | VARCHAR(20) | No | ACTIVE / CLOSED / EXPIRED |

---

# Constraints

Primary Key

```sql
PRIMARY KEY(session_id)
```

Foreign Key

```sql
FOREIGN KEY(user_id)
REFERENCES users(user_id)
```

---

# Indexes

```text
IDX_SESSION_USER

IDX_SESSION_STATUS

IDX_SESSION_LAST_ACTIVITY

IDX_SESSION_TOKEN
```

---

# Relationships

User

↓

Many Sessions

---

# SQL Definition

```sql
CREATE TABLE sessions
(
    session_id UUID PRIMARY KEY,

    user_id BIGINT NOT NULL,

    session_token UUID NOT NULL UNIQUE,

    client_ip VARCHAR(50) NOT NULL,

    client_host VARCHAR(255),

    operating_system VARCHAR(100),

    application_version VARCHAR(50),

    login_time TIMESTAMP NOT NULL,

    last_activity TIMESTAMP NOT NULL,

    logout_time TIMESTAMP,

    expires_at TIMESTAMP NOT NULL,

    status VARCHAR(20) NOT NULL,

    CONSTRAINT fk_sessions_user
        FOREIGN KEY(user_id)
        REFERENCES users(user_id)
);
```

---

# Future Enhancements

- JWT Support
- Device Tracking
- Refresh Tokens
- Session Revocation
- Concurrent Session Limits