# Database Architecture

**Project:** MATLA Chat Platform

**Version:** 1.0

**Database:** PostgreSQL

**Author:** MATLA SYSTEMS

---

# Purpose

The database provides persistent storage for the Chat Platform.

Responsibilities include:

- User Management
- Authentication
- Sessions
- Message History
- File Metadata
- Audit Logging
- System Configuration

The database should never contain business logic. It only stores and retrieves data.

---

# Database Goals

- Data Integrity
- High Performance
- Scalability
- Normalized Schema
- Easy Maintenance
- Transaction Safety
- Future Expansion

---

# Architecture

```

Java Application
│
├── Repository Layer
│
├── JDBC
│
└── PostgreSQL

```

Business Services never communicate directly with PostgreSQL.

All communication passes through repositories.

---

# Database Schema

```

chat_platform

├── users

├── sessions

├── messages

├── files

├── audit_logs

├── roles

├── user_roles

└── system_settings

```

---

# Entity Relationship Diagram

```

users
│
├── sessions

├── messages

├── files

└── user_roles
│
roles

```

---

# Table: users

Stores application users.

| Column | Type |
|----------|------------|
| id | BIGSERIAL |
| username | VARCHAR(50) |
| email | VARCHAR(255) |
| password_hash | VARCHAR(255) |
| first_name | VARCHAR(100) |
| last_name | VARCHAR(100) |
| status | VARCHAR(20) |
| created_at | TIMESTAMP |
| updated_at | TIMESTAMP |

Indexes

- username
- email

Constraints

- username UNIQUE
- email UNIQUE

---

# Table: sessions

Tracks active user sessions.

| Column | Type |
|----------|------------|
| id | BIGSERIAL |
| user_id | BIGINT |
| session_token | UUID |
| socket_id | VARCHAR |
| login_time | TIMESTAMP |
| last_seen | TIMESTAMP |
| status | VARCHAR |

Relationship

```

User

↓

Many Sessions

```

---

# Table: messages

Stores all chat messages.

| Column | Type |
|----------|------------|
| id | BIGSERIAL |
| sender_id | BIGINT |
| receiver_id | BIGINT |
| room_id | BIGINT |
| message | TEXT |
| message_type | VARCHAR |
| created_at | TIMESTAMP |

Types

- Broadcast
- Private
- System

---

# Table: files

Stores uploaded file metadata.

| Column | Type |
|----------|------------|
| id | BIGSERIAL |
| sender_id | BIGINT |
| receiver_id | BIGINT |
| filename | VARCHAR |
| file_size | BIGINT |
| content_type | VARCHAR |
| storage_path | VARCHAR |
| uploaded_at | TIMESTAMP |

Files are stored on disk.

Database stores metadata only.

---

# Table: audit_logs

Stores system activity.

| Column | Type |
|----------|------------|
| id | BIGSERIAL |
| username | VARCHAR |
| action | VARCHAR |
| details | TEXT |
| created_at | TIMESTAMP |

Example

- Login
- Logout
- File Upload
- Connection Lost
- Failed Authentication

---

# Table: roles

Stores system roles.

| Role |
|-------|
| USER |
| ADMIN |
| SUPER_ADMIN |

---

# Table: user_roles

Many-to-many relationship.

```

users

↓

user_roles

↓

roles

```

---

# Table: system_settings

Stores configurable settings.

Example

| Key | Value |
|------|---------|
| heartbeat_interval | 30000 |
| max_upload_size | 10485760 |
| max_connections | 500 |

---

# Relationships

```

users

│

├──────────── sessions

│

├──────────── messages

│

├──────────── files

│

└──────────── user_roles

↓

roles

```

---

# Repository Layer

Each table has its own repository.

```

UserRepository

SessionRepository

MessageRepository

FileRepository

AuditRepository

RoleRepository

```

Repositories encapsulate SQL.

---

# Transactions

Use transactions for

- User Registration
- Login
- File Upload
- Message Storage

Example

```

BEGIN

↓

Insert User

↓

Insert Session

↓

Commit

```

Rollback on failure.

---

# Index Strategy

Create indexes for

- username
- email
- session_token
- sender_id
- receiver_id
- created_at

---

# Future Improvements

Version 2

- Partition messages
- Full-text search
- Message reactions
- Read receipts

Version 3

- Database replication
- Sharding
- Redis caching
- Object storage for files

---

# Summary

The database is responsible only for persistence.

Business rules belong in the Service Layer.

Repositories isolate SQL from the application.

This design supports future growth while remaining simple for the MVP.
