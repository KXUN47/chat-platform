# Normalize the Database

**Document Version:** 1.0  
**Project:** MATLA Chat Platform  
**Module:** Database Design  
**Phase:** SDLC Phase 5 – Database Design

---

# 1. Purpose

Database normalization is the process of organizing data to eliminate redundancy, improve data integrity, and simplify maintenance.

For the MATLA Chat Platform, the database is designed to satisfy **Third Normal Form (3NF)** while remaining scalable for future enterprise features such as:

- Group Chat
- Channels
- Read Receipts
- Voice Messages
- Video Calls
- Notifications
- Distributed Services

---

# 2. Objectives

The normalized database should:

- Eliminate duplicated data
- Maintain referential integrity
- Simplify updates
- Prevent insertion anomalies
- Prevent deletion anomalies
- Prevent update anomalies
- Improve maintainability
- Support future expansion

---

# 3. Benefits of Normalization

| Benefit | Description |
|----------|-------------|
| Reduced Data Duplication | Data stored only once |
| Improved Consistency | Single source of truth |
| Easier Maintenance | Changes occur in one place |
| Better Data Integrity | Prevents inconsistent records |
| Smaller Storage | Less duplicate information |
| Improved Scalability | Easier to extend |

---

# 4. Normalization Levels

The MATLA Chat Platform follows:

- First Normal Form (1NF)
- Second Normal Form (2NF)
- Third Normal Form (3NF)

---

# 5. First Normal Form (1NF)

## Definition

A table is in First Normal Form when:

- Every column contains a single value.
- Every row is unique.
- There are no repeating groups.
- Every column contains atomic values.

---

## Incorrect Design

```text
Users

-------------------------------------------------------
ID | Username | Emails
-------------------------------------------------------
1  | John     | john@gmail.com, john@company.com
```

Problems

- Multiple values stored in one column
- Difficult searching
- Difficult indexing
- Difficult updates

---

## Correct Design

Users

| user_id | username |
|---------|----------|
| 1 | john |

UserEmails

| email_id | user_id | email |
|----------|---------|-------------------|
| 1 | 1 | john@gmail.com |
| 2 | 1 | john@company.com |

---

## Chat Application Example

Incorrect

```text
Connected Users

1,5,7,12
```

Correct

Sessions Table

| session_id | user_id |
|------------|---------|
| 101 | 1 |
| 102 | 5 |
| 103 | 7 |

---

# 6. Second Normal Form (2NF)

## Definition

A table is in Second Normal Form when:

- It is already in 1NF.
- Every non-key column depends on the entire primary key.

---

## Incorrect Example

Messages

| sender_id | receiver_id | sender_name |
|-----------|-------------|-------------|

Problem

sender_name depends only on sender_id.

It does not depend on the complete key.

---

## Correct Design

Users

| user_id | username |
|----------|----------|

Messages

| message_id | sender_id | receiver_id |

User names remain in Users.

Messages reference users.

---

# 7. Third Normal Form (3NF)

## Definition

A table is in Third Normal Form when:

- It is already in 2NF.
- No non-key attribute depends on another non-key attribute.

---

## Incorrect Design

Users

| user_id | username | department_name |

Department Name depends on Department.

Not User.

---

Correct

Departments

| department_id | department_name |

Users

| user_id | department_id |

---

# 8. Normalization Applied to MATLA Chat Platform

---

## Users

Stores

- Username
- Password Hash
- Email
- Status

Does NOT store

- Messages
- Sessions
- Files

Reason

Each belongs in its own table.

---

## Sessions

Stores

- Login Time
- Last Seen
- Connection Status

Does NOT store

- Username
- Email
- Password

These already exist in Users.

---

## Messages

Stores

- Sender
- Receiver
- Message
- Timestamp

Does NOT store

- Sender Name
- Receiver Name

Instead

sender_id references Users.

receiver_id references Users.

---

## Files

Stores

- File Name
- File Size
- File Path

Does NOT duplicate

Sender information.

Receiver information.

Instead references Users.

---

## Audit Logs

Stores

- User ID
- Action
- Timestamp

Does NOT duplicate User information.

---

# 9. Example of Data Redundancy

Poor Design

Messages

| sender_name | sender_email |

Suppose

John changes email.

Every message ever sent must also change.

This creates:

- Update anomalies
- Data inconsistency
- Expensive updates

---

Correct Design

Messages

| sender_id |

Users

| user_id | email |

Only Users table changes.

---

# 10. Database Relationships

Users

```text
1 --------- N Sessions
```

Users

```text
1 --------- N Messages Sent
```

Users

```text
1 --------- N Messages Received
```

Users

```text
1 --------- N Files
```

Users

```text
1 --------- N Audit Logs
```

---

# 11. Avoiding Update Anomalies

Incorrect

```text
John

Email

john@gmail.com

Appears in

5000 messages.
```

Updating email requires

5000 updates.

---

Correct

Email stored once.

Messages reference User ID.

---

# 12. Avoiding Delete Anomalies

Incorrect

Deleting the last message also removes user information.

---

Correct

Users exist independently.

Messages may be deleted safely.

---

# 13. Avoiding Insert Anomalies

Incorrect

Cannot create a user until a message exists.

---

Correct

Users can exist without messages.

Messages can exist only after users exist.

---

# 14. Final Normalized Tables

```text
users
sessions
messages
files
audit_logs
```

Future

```text
groups
channels
roles
permissions
notifications
devices
```

---

# 15. Future Expansion

The current normalized model allows future additions without restructuring existing tables.

Examples

- Group messaging
- Multi-device login
- Read receipts
- Reactions
- Message edits
- Message deletion history

---

# 16. Normalization Checklist

| Item | Status |
|------|--------|
| Atomic Values | ✓ |
| No Repeating Groups | ✓ |
| Unique Primary Keys | ✓ |
| Foreign Keys | ✓ |
| No Partial Dependencies | ✓ |
| No Transitive Dependencies | ✓ |
| Data Integrity | ✓ |
| Future Scalability | ✓ |

---

# 17. Summary

The MATLA Chat Platform database is normalized to **Third Normal Form (3NF)**.

This ensures:

- Minimal redundancy
- High data integrity
- Easier maintenance
- Better performance
- Enterprise scalability
- Clean relationships between entities
- Simplified future feature development