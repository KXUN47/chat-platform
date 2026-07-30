# Authentication Flow

**Document Version:** 1.0  
**Project:** MATLA Chat Platform  
**Author:** MATLA Systems Development

---

# Overview

The Authentication Flow defines how users securely establish a session with the Chat Server before interacting with the system.

The objectives are to:

- Verify user identity
- Protect user credentials
- Create authenticated sessions
- Prevent unauthorized access
- Track active users

---

# Actors

| Actor | Description |
|--------|-------------|
| Client | Desktop application used by the user |
| Authentication Service | Validates user credentials |
| User Repository | Retrieves user information from the database |
| Session Service | Creates and manages active sessions |
| PostgreSQL | Stores user accounts and session information |

---

# Components

```text
Client

↓

Socket Connection

↓

Protocol Decoder

↓

AuthenticationService

↓

UserRepository

↓

PostgreSQL

↓

SessionService

↓

Protocol Encoder

↓

Client
```

---

# Authentication Sequence

```text
User

↓

Open Client

↓

Connect to Server

↓

Server Accepts Connection

↓

User Enters Username

↓

User Enters Password

↓

LOGIN Request

↓

AuthenticationService

↓

UserRepository

↓

Password Verification

↓

Session Creation

↓

LOGIN_SUCCESS Response

↓

User Connected
```

---

# Detailed Flow

## Step 1

Client starts.

---

## Step 2

Client connects to the TCP server.

```text
Socket

↓

ServerSocket.accept()
```

---

## Step 3

Connection Manager registers the connection.

Example

```text
Connection ID

IP Address

Port

Connected Time
```

---

## Step 4

Client displays Login Screen.

User enters

- Username
- Password

---

## Step 5

Client sends LOGIN request.

Example JSON

```json
{
    "command":"LOGIN",
    "username":"john",
    "password":"Password123"
}
```

---

## Step 6

Protocol Layer validates the request.

Checks

- Valid JSON
- Required fields
- Supported command

If validation fails

```json
{
    "status":"ERROR",
    "message":"Invalid request"
}
```

---

## Step 7

AuthenticationService executes.

Responsibilities

- Validate username
- Load user
- Verify password
- Create session

---

## Step 8

UserRepository queries PostgreSQL.

```sql
SELECT *
FROM users
WHERE username = ?;
```

---

## Step 9

Password Verification

Stored password

```text
$2a$10$...
```

Incoming password

```text
Password123
```

Verification

```java
BCrypt.checkpw(password, storedHash);
```

---

## Step 10

If authentication succeeds

Create Session

```text
Session ID

User ID

Socket

Login Time

Status = ONLINE
```

Store session

```sql
INSERT INTO sessions (...)
```

---

## Step 11

Return success.

```json
{
    "status":"SUCCESS",
    "command":"LOGIN",
    "message":"Login successful"
}
```

---

## Step 12

Client enters Chat Screen.

Server broadcasts

```text
John is online.
```

---

# Failed Authentication

Possible failures

## User not found

```json
{
    "status":"ERROR",
    "message":"User does not exist"
}
```

---

## Incorrect password

```json
{
    "status":"ERROR",
    "message":"Invalid username or password"
}
```

---

## User already logged in

```json
{
    "status":"ERROR",
    "message":"User already connected"
}
```

---

## Account disabled

```json
{
    "status":"ERROR",
    "message":"Account disabled"
}
```

---

# Session Lifecycle

```text
LOGIN

↓

Authenticated

↓

Session Created

↓

User Online

↓

Heartbeat

↓

Logout

↓

Session Destroyed
```

---

# Security

- Passwords are never stored in plaintext.
- Passwords are hashed using BCrypt.
- Session IDs are unique.
- Credentials are never written to logs.
- Invalid requests are rejected.
- Session timeout removes inactive users.

---

# Related Components

- AuthenticationService
- UserRepository
- SessionService
- ConnectionManager
- ProtocolDecoder
- ProtocolEncoder
