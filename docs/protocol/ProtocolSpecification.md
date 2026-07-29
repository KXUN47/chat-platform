# Protocol Specification

**Project:** MATLA Chat Platform

**Protocol Version:** 1.0

**Transport:** TCP

**Serialization:** JSON

---

# 1. Overview

The Communication Protocol defines how clients and servers exchange information.

Every packet consists of:

- Command
- Metadata
- Payload

The protocol is request-response based, with additional server push notifications.

---

# 2. Packet Structure

Every packet follows the same structure.

```json
{
  "id":"UUID",
  "timestamp":"2026-07-29T12:00:00Z",
  "command":"LOGIN",
  "status":"REQUEST",
  "payload":{}
}
```

---

# 3. Packet Fields

| Field | Description |
|--------|-------------|
| id | Unique packet identifier |
| timestamp | ISO-8601 timestamp |
| command | Operation being performed |
| status | REQUEST, RESPONSE, EVENT, ERROR |
| payload | Command-specific data |

---

# 4. Packet Types

| Type | Purpose |
|------|---------|
| REQUEST | Client request |
| RESPONSE | Server response |
| EVENT | Server notification |
| ERROR | Failure |

---

# 5. Supported Commands

Authentication

- REGISTER
- LOGIN
- LOGOUT

Messaging

- SEND_MESSAGE
- PRIVATE_MESSAGE
- MESSAGE_HISTORY

Users

- USER_LIST
- USER_STATUS

Files

- FILE_UPLOAD
- FILE_DOWNLOAD

Heartbeat

- PING
- PONG

Administration

- SERVER_INFO

---

# 6. Authentication

## LOGIN Request

```json
{
  "id":"123",
  "command":"LOGIN",
  "status":"REQUEST",
  "payload":{
    "username":"john",
    "password":"password123"
  }
}
```

---

## LOGIN Response

```json
{
  "id":"123",
  "command":"LOGIN",
  "status":"RESPONSE",
  "payload":{
    "success":true,
    "sessionId":"abc123",
    "message":"Login successful"
  }
}
```

---

# 7. Broadcast Messaging

## Request

```json
{
  "command":"SEND_MESSAGE",
  "status":"REQUEST",
  "payload":{
    "message":"Hello everyone!"
  }
}
```

---

## Event

```json
{
  "command":"SEND_MESSAGE",
  "status":"EVENT",
  "payload":{
    "sender":"john",
    "message":"Hello everyone!",
    "timestamp":"2026-07-29T12:00:00Z"
  }
}
```

---

# 8. Private Messaging

```json
{
  "command":"PRIVATE_MESSAGE",
  "status":"REQUEST",
  "payload":{
    "recipient":"alice",
    "message":"Hi Alice!"
  }
}
```

---

# 9. User List

Request

```json
{
  "command":"USER_LIST",
  "status":"REQUEST",
  "payload":{}
}
```

Response

```json
{
  "command":"USER_LIST",
  "status":"RESPONSE",
  "payload":{
    "users":[
      "john",
      "alice",
      "bob"
    ]
  }
}
```

---

# 10. File Upload

Metadata packet

```json
{
  "command":"FILE_UPLOAD",
  "status":"REQUEST",
  "payload":{
    "filename":"document.pdf",
    "size":524288,
    "recipient":"alice"
  }
}
```

Future enhancement:

- Chunked file transfer
- Resume uploads
- Compression

---

# 11. Heartbeat

Server

```json
{
  "command":"PING",
  "status":"EVENT",
  "payload":{}
}
```

Client

```json
{
  "command":"PONG",
  "status":"REQUEST",
  "payload":{}
}
```

---

# 12. Error Packet

```json
{
  "command":"LOGIN",
  "status":"ERROR",
  "payload":{
    "code":"AUTH-001",
    "message":"Invalid username or password"
  }
}
```

---

# 13. Status Codes

| Code | Meaning |
|------|---------|
| REQUEST | Incoming request |
| RESPONSE | Successful response |
| EVENT | Notification |
| ERROR | Failed request |

---

# 14. Error Codes

| Code | Description |
|------|-------------|
| AUTH-001 | Invalid credentials |
| AUTH-002 | User already logged in |
| USER-001 | User not found |
| MSG-001 | Empty message |
| FILE-001 | File too large |
| NET-001 | Connection lost |
| SYS-001 | Internal server error |

---

# 15. Protocol Rules

- Every request must contain a unique packet ID.
- Every response references the originating request ID.
- Unknown commands return an ERROR packet.
- Payload must match the command schema.
- Commands are case-sensitive.
- Clients must authenticate before sending messages.

---

# 16. Protocol Versioning

Future versions should include:

```json
{
  "protocolVersion":"1.0"
}
```

to maintain backward compatibility.

---

# Summary

The protocol provides:

- Consistent packet structure
- Request-response messaging
- Server events
- Extensible command model
- Standardized error handling
- Versioning support
