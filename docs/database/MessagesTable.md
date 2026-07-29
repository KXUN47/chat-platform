# MessagesTable.md

# Messages Table Design

## Purpose

The `messages` table stores every message exchanged through the chat platform.

Supports:

- Broadcast messages
- Private messages
- System notifications
- Future group messages

---

# Business Rules

- Every message has one sender.
- Receiver is optional for broadcasts.
- Messages are immutable after sending.
- Soft deletion only.

---

# Table Name

```text
messages
```

Primary Key

```text
message_id
```

---

# Columns

| Column | Type | Nullable | Description |
|----------|---------|----------|-------------|
| message_id | BIGSERIAL | No | Primary Key |
| sender_id | BIGINT | No | Sender |
| receiver_id | BIGINT | Yes | Receiver |
| message_type | VARCHAR(20) | No | PRIVATE / BROADCAST / SYSTEM |
| message_body | TEXT | No | Chat Content |
| delivered | BOOLEAN | No | Delivery Status |
| delivered_at | TIMESTAMP | Yes | Delivery Time |
| read_status | BOOLEAN | No | Read Receipt |
| read_at | TIMESTAMP | Yes | Read Time |
| created_at | TIMESTAMP | No | Sent Time |
| updated_at | TIMESTAMP | No | Modified |
| deleted_at | TIMESTAMP | Yes | Soft Delete |

---

# Constraints

```sql
PRIMARY KEY(message_id)
```

Foreign Keys

```sql
FOREIGN KEY(sender_id)

REFERENCES users(user_id)
```

```sql
FOREIGN KEY(receiver_id)

REFERENCES users(user_id)
```

---

# Indexes

```text
IDX_MESSAGES_SENDER

IDX_MESSAGES_RECEIVER

IDX_MESSAGES_CREATED

IDX_MESSAGES_TYPE
```

---

# Relationships

User

↓

Many Messages Sent

↓

Many Messages Received

---

# SQL Definition

```sql
CREATE TABLE messages
(
    message_id BIGSERIAL PRIMARY KEY,

    sender_id BIGINT NOT NULL,

    receiver_id BIGINT,

    message_type VARCHAR(20) NOT NULL,

    message_body TEXT NOT NULL,

    delivered BOOLEAN NOT NULL DEFAULT FALSE,

    delivered_at TIMESTAMP,

    read_status BOOLEAN NOT NULL DEFAULT FALSE,

    read_at TIMESTAMP,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    deleted_at TIMESTAMP,

    CONSTRAINT fk_sender
        FOREIGN KEY(sender_id)
        REFERENCES users(user_id),

    CONSTRAINT fk_receiver
        FOREIGN KEY(receiver_id)
        REFERENCES users(user_id)
);
```

---

# Future Enhancements

- Message Reactions
- Threaded Replies
- Attachments
- Edited Messages
- Quoted Messages
- Encryption Metadata