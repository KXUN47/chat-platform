# Messaging Flow

**Document Version:** 1.0  
**Project:** MATLA Chat Platform

---

# Overview

The Messaging Flow defines how messages move between connected users through the Chat Server.

Supported message types

- Broadcast
- Private
- System
- Future Group Messaging

---

# Components

```text
Client

↓

Socket

↓

Protocol Decoder

↓

MessagingService

↓

SessionManager

↓

ConnectionManager

↓

Protocol Encoder

↓

Recipient
```

---

# Broadcast Message Flow

```text
Sender

↓

MESSAGE Packet

↓

Protocol Decoder

↓

MessagingService

↓

Connected Sessions

↓

Broadcast

↓

Recipients
```

---

# Detailed Broadcast Flow

## Step 1

User types message.

```text
Hello Everyone
```

---

## Step 2

Client sends

```json
{
    "command":"MESSAGE",
    "message":"Hello Everyone"
}
```

---

## Step 3

Protocol validates packet.

Checks

- JSON
- Message length
- Required fields

---

## Step 4

MessagingService receives packet.

Responsibilities

- Validate sender
- Validate session
- Store message
- Broadcast

---

## Step 5

Store message.

```sql
INSERT INTO messages(...)
```

---

## Step 6

ConnectionManager retrieves online users.

```text
Online Users

↓

Loop

↓

Send Packet
```

---

## Step 7

Recipients receive

```json
{
    "command":"MESSAGE",
    "from":"john",
    "message":"Hello Everyone"
}
```

---

# Private Messaging Flow

```text
Sender

↓

PRIVATE_MESSAGE

↓

MessagingService

↓

Recipient Lookup

↓

Recipient Socket

↓

Send

↓

Delivery Confirmation
```

---

## Request

```json
{
    "command":"PRIVATE_MESSAGE",
    "to":"alice",
    "message":"Hello"
}
```

---

## Response

```json
{
    "status":"SUCCESS"
}
```

---

# Offline User

If recipient offline

Store

```sql
INSERT INTO private_messages(...)
```

Later

Deliver when user reconnects.

---

# Validation

MessagingService validates

- Logged in
- Message length
- Empty messages
- Illegal characters
- Maximum size

---

# Error Responses

Recipient not online

```json
{
    "status":"ERROR",
    "message":"User offline"
}
```

---

Invalid message

```json
{
    "status":"ERROR",
    "message":"Message cannot be empty"
}
```

---

Unauthorized

```json
{
    "status":"ERROR",
    "message":"Authentication required"
}
```

---

# Message Lifecycle

```text
Compose

↓

Validate

↓

Send

↓

Server

↓

Persist

↓

Deliver

↓

Acknowledgement
```

---

# Future Enhancements

- Read receipts
- Typing indicators
- Emoji support
- Group chats
- Message reactions
- Message editing
- Message deletion
- End-to-end encryption

---

# Related Components

- MessagingService
- SessionService
- ConnectionManager
- MessageRepository
- ProtocolDecoder
- ProtocolEncoder
