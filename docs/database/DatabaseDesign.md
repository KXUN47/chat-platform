# Database Design Document

**Project:** MATLA Chat Platform  
**Document:** Database Purpose, Business Entities & Entity Relationships  
**Version:** 1.0  
**Author:** MATLA Systems Development  
**Database:** PostgreSQL 17+  
**Status:** Approved

---

# 1. Database Purpose

## 1.1 Overview

The database is the persistent storage layer of the MATLA Chat Platform. Its primary responsibility is to securely store, organize, and manage all application data required by the chat server while ensuring consistency, integrity, reliability, and scalability.

Unlike the networking layer, which manages real-time communication between connected clients, the database is responsible for maintaining permanent records that remain available after users disconnect or the server restarts.

The database forms the foundation of the Persistence Layer within the application's layered architecture.

---

## 1.2 Objectives

The database has the following objectives:

- Persist application data
- Maintain data integrity
- Provide fast data retrieval
- Support concurrent users
- Enable future scalability
- Provide reliable auditing
- Maintain referential integrity
- Simplify reporting
- Support backup and recovery
- Allow future feature expansion

---

## 1.3 Responsibilities

The database is responsible for storing:

- Registered users
- User authentication information
- User sessions
- Connected client information
- Broadcast messages
- Private messages
- File metadata
- Server activity logs
- Audit records
- Future application settings

---

## 1.4 Design Principles

The database is designed according to the following principles:

### Data Integrity

All data must remain accurate and consistent throughout the lifetime of the application.

This is achieved through:

- Primary Keys
- Foreign Keys
- Constraints
- Transactions
- Validation

---

### Normalization

The database is normalized to Third Normal Form (3NF) to eliminate redundancy and improve maintainability.

Benefits include:

- Reduced duplication
- Easier updates
- Better consistency
- Improved storage efficiency

---

### Performance

The database is optimized for high read and write throughput.

Performance techniques include:

- Indexing
- Query optimization
- Connection pooling
- Prepared statements
- Efficient relationships

---

### Scalability

The database must support future growth without major redesign.

Future expansion includes:

- Group messaging
- Channels
- Roles
- Permissions
- Notifications
- Mobile applications
- REST APIs
- Microservices

---

### Security

Sensitive information must never be stored in plain text.

Security includes:

- Password hashing
- Secure authentication
- User authorization
- SQL injection prevention
- Least privilege database accounts

---

## 1.5 Scope

The database supports the following application modules:

- Authentication
- User Management
- Messaging
- Session Management
- File Transfer
- Audit Logging
- Administration

---

## 1.6 Database Technology

| Item | Value |
|------|-------|
| Database | PostgreSQL |
| Version | 17+ |
| Access | JDBC |
| Connection Pool | HikariCP |
| Migration Tool | Flyway |
| Character Encoding | UTF-8 |
| Time Zone | UTC |

---

## 1.7 High-Level Architecture

```text
                    Java Server
                         │
                         │
                Business Services
                         │
                         │
                 Repository Layer
                         │
                         │
                     JDBC Driver
                         │
                         │
                 PostgreSQL Database
                         │
        ┌────────────────┼────────────────┐
        │                │                │
      Users          Messages         Sessions
        │                │                │
        └────────────┬───┴───────────────┘
                     │
                 File Metadata
                     │
                 Audit Logging
```

---

# 2. Business Entities

## 2.1 Overview

Business entities represent the core objects managed by the chat application.

Each entity corresponds to a real-world concept and is typically mapped to one database table and one Java entity class.

---

## 2.2 User

### Description

Represents a registered application user.

Every authenticated person using the system is stored as a User.

---

### Responsibilities

- Login
- Logout
- Send messages
- Receive messages
- Upload files
- Download files
- Maintain profile

---

### Primary Attributes

- User ID
- Username
- Email
- Password Hash
- Status
- Created Date
- Updated Date

---

### Relationships

A User can:

- own many Sessions
- send many Messages
- receive many Messages
- upload many Files
- generate many Audit Logs

---

## 2.3 Session

### Description

Represents a login session for a connected client.

A user may have multiple sessions over time.

---

### Responsibilities

- Track active logins
- Store login timestamp
- Store logout timestamp
- Track heartbeat
- Monitor activity
- Support reconnect

---

### Primary Attributes

- Session ID
- User ID
- Login Time
- Logout Time
- Last Seen
- IP Address
- Status

---

### Relationships

Each Session belongs to exactly one User.

---

## 2.4 Message

### Description

Represents communication between users.

Messages may be:

- Broadcast
- Private
- System generated

---

### Responsibilities

- Store message content
- Track sender
- Track receiver
- Store timestamps
- Maintain history

---

### Primary Attributes

- Message ID
- Sender ID
- Receiver ID
- Message Type
- Message Body
- Sent Time

---

### Relationships

Each Message:

- has one Sender
- optionally has one Receiver
- may contain one File
- belongs to one conversation

---

## 2.5 File

### Description

Represents metadata for transferred files.

The actual binary files remain on disk.

---

### Responsibilities

- Store filename
- Store location
- Store size
- Track ownership
- Track uploads

---

### Primary Attributes

- File ID
- Sender ID
- Receiver ID
- File Name
- File Path
- File Size
- Upload Time

---

### Relationships

Each File:

- belongs to one sender
- belongs to one receiver
- may belong to one message

---

## 2.6 Audit Log

### Description

Stores security and operational events.

Audit logs provide accountability and troubleshooting capabilities.

---

### Responsibilities

Record:

- Login
- Logout
- Authentication failures
- Message sending
- File uploads
- Downloads
- Administrative actions

---

### Primary Attributes

- Audit ID
- User ID
- Action
- Description
- Timestamp

---

### Relationships

Each Audit Log belongs to one User.

---

## 2.7 Future Business Entities

The architecture allows future expansion through additional entities.

Examples include:

| Entity | Purpose |
|---------|----------|
| Group | Group Chats |
| Channel | Topic Channels |
| Role | User Permissions |
| Permission | Authorization |
| Notification | Alerts |
| Device | Mobile Devices |
| Friend | Contact Lists |
| Conversation | Chat Threads |
| Reaction | Emoji Reactions |
| Attachment | Multiple Files |

---

# 3. Entity Relationships

## 3.1 Overview

Entity relationships define how business entities interact with one another.

These relationships ensure referential integrity while allowing efficient querying and future scalability.

---

## 3.2 Relationship Diagram

```text
                       +----------------+
                       |     USERS      |
                       +----------------+
                       | PK user_id     |
                       +----------------+
                          │
          ┌───────────────┼──────────────────┐
          │               │                  │
          │               │                  │
         1│              1│                 1│
          │               │                  │
         *│              *│                 *│
          ▼               ▼                  ▼

 +----------------+ +----------------+ +----------------+
 |    SESSIONS    | |    MESSAGES    | |      FILES     |
 +----------------+ +----------------+ +----------------+
 | PK session_id  | | PK message_id  | | PK file_id     |
 | FK user_id     | | FK sender_id   | | FK sender_id   |
 +----------------+ | FK receiver_id | | FK receiver_id |
                    +----------------+ +----------------+
                             │
                             │
                             │
                             ▼
                     +----------------+
                     |  AUDIT_LOGS    |
                     +----------------+
                     | PK audit_id    |
                     | FK user_id     |
                     +----------------+
```

---

## 3.3 User → Session

### Relationship

One-to-Many

```text
One User

↓

Many Sessions
```

Example

```text
John

Session A

Session B

Session C
```

---

## 3.4 User → Message (Sender)

Relationship

One-to-Many

```text
User

↓

Many Sent Messages
```

---

## 3.5 User → Message (Receiver)

Relationship

One-to-Many

```text
User

↓

Many Received Messages
```

---

## 3.6 User → Files

Relationship

One-to-Many

A user may upload multiple files.

---

## 3.7 User → Audit Logs

Relationship

One-to-Many

Every significant action performed by a user is recorded.

---

## 3.8 Message → File

Relationship

One-to-One (Optional)

```text
Message

↓

Optional File
```

Not every message contains a file.

---

## 3.9 Relationship Matrix

| Parent | Child | Relationship |
|---------|-------|--------------|
| User | Session | One-to-Many |
| User | Sent Messages | One-to-Many |
| User | Received Messages | One-to-Many |
| User | Files | One-to-Many |
| User | Audit Logs | One-to-Many |
| Message | File | Zero-or-One |

---

## 3.10 Referential Integrity

The following foreign key relationships are enforced.

| Child Table | Foreign Key | Parent Table |
|-------------|-------------|--------------|
| sessions | user_id | users |
| messages | sender_id | users |
| messages | receiver_id | users |
| files | sender_id | users |
| files | receiver_id | users |
| files | message_id | messages |
| audit_logs | user_id | users |

---

## 3.11 Cascade Strategy

The application will use controlled cascading to protect historical records.

| Operation | Strategy |
|-----------|----------|
| Delete User | Restrict (prevent deletion if dependent records exist) |
| Delete Session | Cascade from user only if explicitly intended |
| Delete Message | Restrict or soft delete |
| Delete File | Remove metadata and associated disk file through application logic |
| Delete Audit Log | Never delete under normal operation |

Soft deletes are recommended for users and messages to preserve auditability and historical reporting.

---

# 4. Summary

The database model establishes a normalized, secure, and scalable foundation for the MATLA Chat Platform. By clearly defining the purpose of the database, identifying core business entities, and modeling their relationships, the persistence layer supports reliable user authentication, real-time messaging, file sharing, and auditing. The design follows enterprise database practices and is intentionally extensible to accommodate future capabilities such as group chats, roles, notifications, REST APIs, and distributed deployments without requiring significant schema redesign.