# UsersTable.md

# Users Table Design

**Document Version:** 1.0  
**Project:** MATLA Chat Platform  
**Module:** chat-database  
**Database:** PostgreSQL 17+  
**Author:** MATLA SYSTEMS DEVELOPMENT

---

# 1. Purpose

The `users` table stores all registered users of the chat platform.

It acts as the primary identity table and is referenced throughout the
database by foreign keys.

This table is responsible for:

- User authentication
- User profile information
- Account status
- Password management
- Account lifecycle

---

# 2. Business Rules

- Every user has a unique username.
- Every user has a unique email address.
- Passwords are never stored in plain text.
- Users may own multiple sessions.
- Users may send many messages.
- Users may receive many messages.
- Users may upload multiple files.
- User records are never physically deleted.
- Accounts are soft-deleted.

---

# 3. Table Definition

Table Name

```text
users
```

Primary Key

```text
user_id
```

---

# 4. Columns

| Column | Type | Nullable | Default | Description |
|----------|---------|----------|----------|-------------|
| user_id | BIGSERIAL | No | Auto | Primary Key |
| username | VARCHAR(50) | No | - | Login username |
| email | VARCHAR(255) | No | - | Email Address |
| password_hash | VARCHAR(255) | No | - | BCrypt / Argon2 Hash |
| first_name | VARCHAR(100) | Yes | NULL | First Name |
| last_name | VARCHAR(100) | Yes | NULL | Last Name |
| display_name | VARCHAR(100) | Yes | NULL | Chat Display Name |
| avatar_url | VARCHAR(500) | Yes | NULL | Profile Image |
| account_status | VARCHAR(20) | No | ACTIVE | ACTIVE / DISABLED / LOCKED |
| online_status | VARCHAR(20) | No | OFFLINE | ONLINE / OFFLINE / AWAY |
| last_seen | TIMESTAMP | Yes | NULL | Last activity |
| failed_login_attempts | INTEGER | No | 0 | Security counter |
| email_verified | BOOLEAN | No | FALSE | Email verification |
| created_at | TIMESTAMP | No | CURRENT_TIMESTAMP | Creation date |
| updated_at | TIMESTAMP | No | CURRENT_TIMESTAMP | Last update |
| deleted_at | TIMESTAMP | Yes | NULL | Soft delete |

---

# 5. Constraints

Primary Key

```sql
PRIMARY KEY (user_id)
```

Unique

```sql
UNIQUE(username)

UNIQUE(email)
```

Not Null

```text
username

email

password_hash

account_status

online_status

created_at
```

Check Constraints

```sql
CHECK (failed_login_attempts >= 0)
```

---

# 6. Indexes

Primary Index

```sql
PK_USERS
```

Additional Indexes

```sql
IDX_USERS_USERNAME

IDX_USERS_EMAIL

IDX_USERS_STATUS

IDX_USERS_LAST_SEEN
```

---

# 7. Relationships

One User

↓

Many Sessions

↓

Many Messages Sent

↓

Many Messages Received

↓

Many Files

↓

Many Audit Logs

---

# 8. Foreign Key References

Referenced By

```text
sessions.user_id

messages.sender_id

messages.receiver_id

files.sender_id

files.receiver_id

audit_logs.user_id
```

---

# 9. SQL Definition

```sql
CREATE TABLE users
(
    user_id BIGSERIAL PRIMARY KEY,

    username VARCHAR(50) NOT NULL UNIQUE,

    email VARCHAR(255) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    first_name VARCHAR(100),

    last_name VARCHAR(100),

    display_name VARCHAR(100),

    avatar_url VARCHAR(500),

    account_status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',

    online_status VARCHAR(20) NOT NULL DEFAULT 'OFFLINE',

    last_seen TIMESTAMP,

    failed_login_attempts INTEGER NOT NULL DEFAULT 0,

    email_verified BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP
);
```

---

# 10. Future Enhancements

- Multi-factor authentication
- Password history
- Roles
- Permissions
- Profile customization
- OAuth accounts
- Account recovery