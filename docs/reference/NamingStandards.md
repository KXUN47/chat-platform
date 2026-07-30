# Define Naming Standards

**Document Version:** 1.0  
**Project:** MATLA Chat Platform  
**Module:** Database Design  
**Phase:** SDLC Phase 5 – Database Design

---

# 1. Purpose

Naming standards define a consistent convention for all database objects.

Consistent naming improves:

- Readability
- Maintainability
- Collaboration
- Documentation
- SQL development
- Debugging

All database objects within the MATLA Chat Platform must follow this standard.

---

# 2. Naming Principles

All names must be:

- Descriptive
- Consistent
- Predictable
- Singular in columns
- Plural for tables
- Lowercase
- Snake case
- Free from abbreviations unless universally accepted

---

# 3. General Rules

## Use lowercase

Correct

```text
users
messages
audit_logs
```

Incorrect

```text
Users
USERS
UserTable
```

---

## Use snake_case

Correct

```text
user_id
created_at
password_hash
file_size
```

Incorrect

```text
userId
CreatedAt
PasswordHash
```

---

## Avoid Spaces

Correct

```text
message_history
```

Incorrect

```text
Message History
```

---

## Avoid Special Characters

Correct

```text
audit_logs
```

Incorrect

```text
audit-logs
audit.logs
```

---

# 4. Table Naming

Tables use:

Plural nouns

Correct

```text
users
sessions
messages
files
audit_logs
```

Incorrect

```text
user
session
tblUsers
chatUsers
```

---

# 5. Primary Key Naming

Every table follows:

```text
<table>_id
```

Examples

| Table | Primary Key |
|---------|-------------|
| users | user_id |
| sessions | session_id |
| messages | message_id |
| files | file_id |
| audit_logs | audit_log_id |

---

# 6. Foreign Key Naming

Foreign keys always reference the primary key name of the parent table.

Examples

```text
user_id
sender_id
receiver_id
message_id
session_id
```

Example

Messages

```text
sender_id

↓

users.user_id
```

---

# 7. Timestamp Naming

Every timestamp ends with:

```text
_at
```

Examples

```text
created_at
updated_at
deleted_at
sent_at
uploaded_at
login_time
last_seen
```

---

# 8. Boolean Naming

Boolean fields should read naturally.

Correct

```text
is_online
is_deleted
is_active
is_verified
has_attachment
```

Incorrect

```text
online
deleted
verified
attachment
```

---

# 9. Status Columns

Status values use:

```text
status
```

Examples

```text
ONLINE
OFFLINE
AWAY
BUSY
DISCONNECTED
```

---

# 10. Password Columns

Never store passwords.

Use

```text
password_hash
```

Never

```text
password
plain_password
```

---

# 11. File Naming

Correct

```text
file_name
file_path
file_size
file_type
mime_type
```

Incorrect

```text
filename
filesize
path
```

---

# 12. Message Naming

Examples

```text
message_text
message_type
message_status
sent_at
```

---

# 13. Session Naming

Examples

```text
session_id
session_token
login_time
logout_time
last_seen
```

---

# 14. Audit Naming

Examples

```text
action
description
created_at
ip_address
```

---

# 15. Constraint Naming

Primary Key

```text
pk_users
pk_messages
pk_sessions
```

Foreign Key

```text
fk_messages_sender
fk_messages_receiver
fk_sessions_user
```

Unique Constraint

```text
uk_users_username
uk_users_email
```

Check Constraint

```text
chk_files_file_size
```

---

# 16. Index Naming

Format

```text
idx_<table>_<column>
```

Examples

```text
idx_users_username
idx_users_email
idx_messages_sender_id
idx_messages_sent_at
idx_sessions_user_id
```

---

# 17. Sequence Naming

If explicit sequences are used:

```text
seq_users
seq_messages
seq_files
```

---

# 18. View Naming

Views begin with:

```text
vw_
```

Examples

```text
vw_active_users
vw_message_statistics
vw_online_sessions
```

---

# 19. Trigger Naming

Format

```text
trg_<table>_<action>
```

Examples

```text
trg_users_insert
trg_messages_delete
trg_sessions_update
```

---

# 20. Function Naming

Functions use verbs.

Examples

```text
get_online_users
calculate_message_count
archive_messages
cleanup_sessions
```

---

# 21. Migration File Naming

Flyway migration format:

```text
V1__Create_users.sql
V2__Create_sessions.sql
V3__Create_messages.sql
V4__Create_files.sql
V5__Create_audit_logs.sql
```

Rules

- Prefix with `V`
- Increment version numbers sequentially
- Use double underscores (`__`) before the description
- Use PascalCase for the description to improve readability

---

# 22. Java Entity Mapping

| Database | Java Entity |
|-----------|-------------|
| users | User |
| sessions | Session |
| messages | Message |
| files | FileMetadata |
| audit_logs | AuditLog |

---

# 23. Repository Naming

Repositories follow:

```text
<Entity>NameRepository
```

Examples

```text
UserRepository
SessionRepository
MessageRepository
FileRepository
AuditLogRepository
```

---

# 24. Service Naming

Business services follow:

```text
<Entity>NameService
```

Examples

```text
UserService
AuthenticationService
MessagingService
FileService
SessionService
AuditService
```

---

# 25. Package Naming

Java packages use lowercase.

```text
com.matlasystems.chat

config

entity

repository

service

controller

protocol

network

exception

util
```

---

# 26. Reserved Words

Never use SQL reserved keywords as identifiers.

Avoid

```text
user
order
group
table
index
select
```

Instead use

```text
users
user_groups
chat_messages
database_indexes
```

---

# 27. Abbreviations

Avoid unnecessary abbreviations.

Correct

```text
message_count
connection_status
authentication_token
```

Incorrect

```text
msg_cnt
conn_stat
auth_tok
```

Use common abbreviations only where widely understood, such as:

```text
id
ip
url
uuid
api
tcp
```

---

# 28. Naming Checklist

| Standard | Status |
|----------|--------|
| Lowercase | ✓ |
| Snake Case | ✓ |
| Plural Tables | ✓ |
| Singular Columns | ✓ |
| Descriptive Names | ✓ |
| Consistent Keys | ✓ |
| Standard Constraint Prefixes | ✓ |
| Standard Index Prefixes | ✓ |
| Standard Migration Names | ✓ |
| Java Mapping Defined | ✓ |

---

# 29. Summary

Following these naming standards ensures the database remains:

- Easy to understand
- Consistent across all modules
- Maintainable by multiple developers
- Compatible with enterprise development practices
- Ready for future expansion and long-term support

By adopting these conventions from the start, SQL scripts, Java entities, repositories, services, and documentation will all follow a predictable structure, reducing development errors and improving team productivity.