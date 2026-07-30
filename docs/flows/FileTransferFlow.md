# File Transfer Flow

**Document Version:** 1.0  
**Project:** MATLA Chat Platform

---

# Overview

The File Transfer Flow describes how files are uploaded, transmitted, stored, and downloaded between users.

Supported operations

- Upload
- Download
- Transfer progress
- Metadata storage

---

# Components

```text
Client

↓

Socket

↓

Protocol Decoder

↓

FileService

↓

File Storage

↓

Metadata Repository

↓

Recipient
```

---

# Upload Flow

```text
Sender

↓

Select File

↓

Metadata Packet

↓

Server Validation

↓

Chunk Upload

↓

File Storage

↓

Notify Recipient
```

---

# Step 1

User selects file.

Example

```text
report.pdf
```

---

# Step 2

Client gathers metadata.

```text
Filename

File Size

Extension

Checksum
```

---

# Step 3

Client sends metadata.

```json
{
    "command":"FILE_UPLOAD",
    "filename":"report.pdf",
    "size":254000,
    "recipient":"alice"
}
```

---

# Step 4

Server validates

- Logged in
- Recipient exists
- File size
- File type

---

# Step 5

Server replies

```json
{
    "status":"READY"
}
```

---

# Step 6

Client uploads file in chunks.

```text
Chunk 1

↓

Chunk 2

↓

Chunk 3

↓

...

↓

Last Chunk
```

---

# Chunk Structure

```text
Chunk Number

Total Chunks

Data

Checksum
```

---

# Step 7

Server writes chunks.

```text
Receive Chunk

↓

Validate

↓

Append

↓

Save
```

---

# Step 8

Server verifies checksum.

If invalid

```text
Reject Upload
```

---

# Step 9

Store metadata.

```sql
INSERT INTO files(...)
```

---

# Step 10

Notify recipient.

```json
{
    "command":"FILE_AVAILABLE",
    "filename":"report.pdf",
    "sender":"john"
}
```

---

# Download Flow

```text
Recipient

↓

Download Request

↓

FileService

↓

Storage

↓

Chunk Stream

↓

Client

↓

Save File
```

---

# Download Request

```json
{
    "command":"FILE_DOWNLOAD",
    "fileId":"123"
}
```

---

# Progress Reporting

Client displays

```text
0%

↓

15%

↓

42%

↓

67%

↓

100%
```

---

# Error Handling

Possible errors

## File too large

```json
{
    "status":"ERROR",
    "message":"Maximum upload size exceeded"
}
```

---

## Invalid file type

```json
{
    "status":"ERROR",
    "message":"Unsupported file type"
}
```

---

## Recipient offline

```json
{
    "status":"ERROR",
    "message":"Recipient not online"
}
```

---

## Upload interrupted

Server

- Deletes partial upload
- Logs failure
- Waits for retry

---

# File Storage

Example structure

```text
/var/chat/uploads/

2026/

07/

29/

UUID-report.pdf
```

---

# Security

- Validate file names
- Validate file extensions
- Enforce file size limits
- Prevent path traversal attacks
- Store files outside the application directory
- Verify checksums after upload
- Log all file operations

---

# Future Enhancements

- Pause and resume uploads
- Compression
- Virus scanning
- File previews
- Thumbnail generation
- Cloud storage support
- Encryption at rest
- Multiple concurrent transfers

---

# Related Components

- FileService
- FileRepository
- ConnectionManager
- SessionService
- ProtocolDecoder
- ProtocolEncoder
- FileStorageManager
