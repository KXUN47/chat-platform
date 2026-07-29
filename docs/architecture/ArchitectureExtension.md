# Database Architecture Extension
**Document Version:** 1.0  
**Project:** MATLA Chat Platform  
**Module:** chat-database  
**Language:** Java 21  
**Database:** PostgreSQL 17+  
**Author:** MATLA Systems Development

---

# Table of Contents

1. Connection Architecture
2. Database Package Structure
3. Java Entity Architecture
4. Repository Architecture
5. Entity Relationships
6. DTO Architecture
7. Future Scalability
8. Scalability Roadmap
9. Database Evolution Strategy

---

# 1. Connection Architecture

## Overview

The Connection Architecture defines how the application communicates with PostgreSQL while ensuring:

- High performance
- Thread safety
- Reliability
- Scalability
- Efficient resource utilization

The application **never creates database connections manually for each request**.

Instead, a **connection pool** manages reusable connections.

---

## Architecture

```text
                    Java Application
                           │
                           │
                    Business Services
                           │
                           │
                     Repository Layer
                           │
                           │
                   Database Manager
                           │
                           │
                 HikariCP Connection Pool
                           │
          ┌────────────────┼────────────────┐
          │                │                │
      Connection 1     Connection 2     Connection N
          │                │                │
          └────────────────┼────────────────┘
                           │
                     PostgreSQL Server
```

---

## Why Connection Pooling?

Without pooling:

```text
Request

↓

Open Database Connection

↓

Execute Query

↓

Close Connection

↓

Repeat...
```

Problems

- Slow
- Expensive
- High CPU usage
- High memory usage
- Poor scalability

---

With HikariCP

```text
Application

↓

Borrow Connection

↓

Execute Query

↓

Return Connection

↓

Reusable Connection
```

Advantages

- Extremely fast
- Minimal overhead
- Production-ready
- Automatic connection reuse
- Thread-safe
- Enterprise standard

---

## Connection Lifecycle

```text
Application Startup

↓

Initialize HikariCP

↓

Create Initial Pool

↓

Repository Requests Connection

↓

Execute SQL

↓

Commit / Rollback

↓

Return Connection

↓

Available for Next Request
```

---

## Connection Pool Configuration

Recommended Values

| Property | Value |
|-----------|------|
| Minimum Idle | 5 |
| Maximum Pool Size | 20 |
| Connection Timeout | 30 Seconds |
| Idle Timeout | 10 Minutes |
| Max Lifetime | 30 Minutes |
| Validation Timeout | 5 Seconds |

---

## Database Manager Responsibilities

The Database Manager is responsible for:

- Creating the connection pool
- Providing connections
- Closing connections
- Monitoring pool health
- Logging database events

It should be implemented as a Singleton.

Example Responsibilities

```text
DatabaseManager

↓

Initialize Pool

↓

Provide Connection

↓

Monitor Pool

↓

Shutdown Pool
```

---

## Transaction Flow

```text
Business Service

↓

Repository

↓

Begin Transaction

↓

Execute SQL

↓

Commit

↓

Return Connection
```

Failure Flow

```text
Business Service

↓

Repository

↓

Execute SQL

↓

SQLException

↓

Rollback

↓

Log Error

↓

Return Connection
```

---

## Thread Safety

Each worker thread borrows its own database connection.

```text
Executor Thread 1

↓

Connection 1

Executor Thread 2

↓

Connection 2

Executor Thread 3

↓

Connection 3
```

Connections are **never shared simultaneously between threads**.

---

## Future Improvements

Future enhancements may include:

- Read replicas
- Master/Replica routing
- Multi-region databases
- Distributed transactions
- Database monitoring dashboards

---

# 2. Database Package Structure

## Overview

The database module should follow a clean layered package structure.

```text
chat-database/

src/

main/

java/

com/

matlasystems/

chat/

database/

│

├── config/

├── connection/

├── entity/

├── dto/

├── repository/

├── mapper/

├── converter/

├── specification/

├── migration/

├── validation/

├── exception/

├── util/

└── security/
```

---

## Package Responsibilities

### config

Application configuration.

Contents

- DatabaseConfig
- PoolConfig
- FlywayConfig

---

### connection

Database connectivity.

Contents

- DatabaseManager
- ConnectionProvider
- TransactionManager

---

### entity

JPA/JDBC entity objects.

Contents

- User
- Session
- Message
- FileMetadata
- AuditLog

---

### dto

Transfer objects.

Contents

- UserDTO
- LoginDTO
- MessageDTO
- FileDTO

---

### repository

Database access layer.

Contents

- UserRepository
- SessionRepository
- MessageRepository
- FileRepository
- AuditRepository

---

### mapper

Converts entities into DTOs.

Example

```text
User

↓

UserMapper

↓

UserDTO
```

---

### converter

Type conversion.

Examples

- Enum converters
- Timestamp converters
- UUID converters

---

### specification

Dynamic query specifications.

Useful later for:

- Search
- Filtering
- Reporting

---

### migration

Database version scripts.

```text
V1__Create_Users.sql

V2__Create_Sessions.sql

V3__Create_Messages.sql

V4__Create_Files.sql
```

---

### validation

Database validation logic.

Examples

- Duplicate username
- Duplicate email
- File size validation

---

### exception

Database exceptions.

Examples

- DatabaseException
- DuplicateUserException
- RecordNotFoundException

---

### util

Database utilities.

Examples

- SQL Helper
- Timestamp Utility
- UUID Utility

---

### security

Security helpers.

Examples

- PasswordHasher
- TokenGenerator

---

# 3. Java Entity Architecture

## Overview

Each database table maps directly to a Java entity.

```text
PostgreSQL Table

↓

Java Entity

↓

Repository

↓

Business Service

↓

Controller
```

---

## Core Entities

```text
User

Session

Message

FileMetadata

AuditLog
```

---

## User Entity

Represents a registered application user.

Responsibilities

- Identity
- Authentication
- Profile
- Status

Fields

```text
userId

username

email

passwordHash

status

createdAt

updatedAt
```

Relationships

```text
User

│

├── Sessions

├── Messages Sent

├── Messages Received

├── Files

└── Audit Logs
```

---

## Session Entity

Represents an authenticated login session.

Fields

```text
sessionId

userId

loginTime

lastSeen

ipAddress

status
```

Purpose

- Active sessions
- Session history
- User activity

---

## Message Entity

Represents every chat message.

Fields

```text
messageId

senderId

receiverId

messageType

messageText

createdAt
```

Supports

- Broadcast
- Private
- System messages

Future

- Edited messages
- Deleted messages
- Reactions

---

## FileMetadata Entity

Represents uploaded files.

Actual binary files remain on disk.

Fields

```text
fileId

senderId

receiverId

messageId

fileName

fileSize

filePath

uploadedAt
```

---

## AuditLog Entity

Tracks system events.

Fields

```text
auditId

userId

action

description

createdAt
```

Examples

```text
LOGIN

LOGOUT

MESSAGE_SENT

UPLOAD

DOWNLOAD

ADMIN_ACTION
```

---

# 4. Repository Architecture

Repositories isolate persistence from business logic.

```text
Business Service

↓

Repository

↓

Database
```

Repositories

```text
UserRepository

SessionRepository

MessageRepository

FileRepository

AuditRepository
```

Repositories should only perform:

- CRUD
- Queries
- Transactions

No business rules belong here.

---

# 5. Entity Relationships

```text
Users
 │
 ├─────────────┐
 │             │
 ▼             ▼
Sessions    Messages
                │
        ┌───────┴────────┐
        ▼                ▼
Files          Audit Logs
```

Cardinality

| Parent | Child | Relationship |
|----------|--------|--------------|
| User | Sessions | 1:N |
| User | Messages | 1:N |
| Message | Files | 1:N (optional) |
| User | Audit Logs | 1:N |

---

# 6. DTO Architecture

DTOs separate the persistence model from the application model.

```text
Database

↓

Entity

↓

Mapper

↓

DTO

↓

Network Response
```

Examples

```text
UserDTO

LoginResponse

MessageDTO

FileDTO

SessionDTO

AuditDTO
```

Advantages

- Prevents exposing entities
- Smaller network payloads
- Better API stability

---

# 7. Future Scalability

The database has been designed for long-term growth.

---

## Phase 1

Current Features

```text
Authentication

Sessions

Messaging

Files

Audit Logs
```

---

## Phase 2

Groups

```text
Groups

↓

Group Members

↓

Group Messages
```

New Tables

```text
groups

group_members

group_messages
```

---

## Phase 3

Channels

```text
channels

channel_members

channel_messages
```

Supports

- Public channels
- Private channels
- Announcements

---

## Phase 4

Presence

New Tables

```text
presence

device_status

last_activity
```

Features

- Online
- Away
- Busy
- Invisible

---

## Phase 5

Notifications

```text
notifications

notification_settings
```

---

## Phase 6

Voice & Video

```text
calls

call_participants

recordings
```

---

## Phase 7

Enterprise Features

```text
roles

permissions

organizations

teams

departments
```

---

## Phase 8

Analytics

```text
analytics

message_statistics

user_statistics

server_statistics
```

---

## Phase 9

Distributed Storage

Future Architecture

```text
Application Cluster

↓

Load Balancer

↓

API Servers

↓

Read Replicas

↓

Primary PostgreSQL

↓

Object Storage

↓

Redis Cache
```

---

# 8. Scalability Roadmap

| Version | Features |
|----------|----------|
| V1 | Authentication, Messaging, File Transfer |
| V2 | Groups, Channels |
| V3 | Notifications, Presence |
| V4 | Voice & Video |
| V5 | Enterprise Organizations |
| V6 | Analytics Dashboard |
| V7 | Distributed Databases |
| V8 | Microservices |
| V9 | Kubernetes Deployment |

---

# 9. Database Evolution Strategy

To support continuous delivery, all schema changes must be version-controlled and repeatable.

## Migration Workflow

```text
Developer

↓

Create Migration Script

↓

Commit to Git

↓

CI/CD Pipeline

↓

Flyway Validation

↓

Apply Migration

↓

Application Startup

↓

Database Updated
```

### Rules

- Never modify an existing migration after it has been applied.
- Create a new migration for every schema change.
- Keep migrations small and focused.
- Test migrations against a copy of production data before release.
- Use semantic versioning for application releases and sequential versioning for database migrations.

### Example Migration Timeline

```text
V1__Create_Users.sql

↓

V2__Create_Sessions.sql

↓

V3__Create_Messages.sql

↓

V4__Create_Files.sql

↓

V5__Create_Audit_Logs.sql

↓

V6__Create_Groups.sql

↓

V7__Create_Notifications.sql
```

---

# Summary

This database architecture provides:

- Enterprise-grade connection management using HikariCP
- A modular and maintainable package structure
- Clear separation between entities, repositories, DTOs, and services
- A scalable relational model ready for future features
- A migration strategy using Flyway for controlled schema evolution
- A roadmap that supports growth from a simple TCP chat server to a distributed enterprise messaging platform