# FilesTable.md

# Files Table Design

## Purpose

The `files` table stores metadata describing files exchanged between users.

Actual files remain on the server file system.

Example

```text
/var/chat/uploads/
```

---

# Business Rules

- File contents are never stored in PostgreSQL.
- Metadata only.
- Files belong to one sender.
- Files belong to one receiver.
- Files may optionally reference a chat message.

---

# Table Name

```text
files
```

Primary Key

```text
file_id
```

---

# Columns

| Column | Type | Nullable | Description |
|----------|---------|----------|-------------|
| file_id | BIGSERIAL | No | Primary Key |
| sender_id | BIGINT | No | Sender |
| receiver_id | BIGINT | Yes | Receiver |
| message_id | BIGINT | Yes | Related Message |
| original_filename | VARCHAR(255) | No | Uploaded Name |
| stored_filename | VARCHAR(255) | No | Server File Name |
| mime_type | VARCHAR(100) | No | Content Type |
| file_extension | VARCHAR(20) | No | Extension |
| file_size | BIGINT | No | Bytes |
| checksum | VARCHAR(128) | Yes | SHA-256 |
| storage_path | VARCHAR(500) | No | Server Path |
| upload_status | VARCHAR(20) | No | COMPLETED / FAILED |
| uploaded_at | TIMESTAMP | No | Upload Time |
| downloaded_at | TIMESTAMP | Yes | Download Time |
| deleted_at | TIMESTAMP | Yes | Soft Delete |

---

# Constraints

Primary Key

```sql
PRIMARY KEY(file_id)
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

```sql
FOREIGN KEY(message_id)

REFERENCES messages(message_id)
```

Check Constraint

```sql
CHECK(file_size > 0)
```

---

# Indexes

```text
IDX_FILES_SENDER

IDX_FILES_RECEIVER

IDX_FILES_MESSAGE

IDX_FILES_FILENAME

IDX_FILES_UPLOADED
```

---

# Relationships

User

↓

Many Files Sent

↓

Many Files Received

↓

Optional Message

---

# SQL Definition

```sql
CREATE TABLE files
(
    file_id BIGSERIAL PRIMARY KEY,

    sender_id BIGINT NOT NULL,

    receiver_id BIGINT,

    message_id BIGINT,

    original_filename VARCHAR(255) NOT NULL,

    stored_filename VARCHAR(255) NOT NULL,

    mime_type VARCHAR(100) NOT NULL,

    file_extension VARCHAR(20) NOT NULL,

    file_size BIGINT NOT NULL,

    checksum VARCHAR(128),

    storage_path VARCHAR(500) NOT NULL,

    upload_status VARCHAR(20) NOT NULL,

    uploaded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    downloaded_at TIMESTAMP,

    deleted_at TIMESTAMP,

    CONSTRAINT fk_files_sender
        FOREIGN KEY(sender_id)
        REFERENCES users(user_id),

    CONSTRAINT fk_files_receiver
        FOREIGN KEY(receiver_id)
        REFERENCES users(user_id),

    CONSTRAINT fk_files_message
        FOREIGN KEY(message_id)
        REFERENCES messages(message_id),

    CONSTRAINT chk_file_size
        CHECK(file_size > 0)
);
```

---

# Future Enhancements

- File versioning
- Virus scan status
- Encryption keys
- Chunked uploads
- Download counters
- Cloud object storage (S3, MinIO, Azure Blob)
- File retention policies