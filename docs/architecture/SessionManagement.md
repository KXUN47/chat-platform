# SessionManagement.md

# Session Management Architecture

**Project:** MATLA Chat Platform

---

# 1. Overview

A session represents an authenticated user's active connection to the server.

Each successful login creates a new session.

Each logout or timeout destroys that session.

---

# 2. Objectives

Provide

- User tracking
- Authentication state
- Connection management
- Session timeout
- Reconnection support

---

# 3. Session Lifecycle

```
Client Starts

↓

Login Request

↓

Authentication

↓

Create Session

↓

Active Session

↓

Heartbeat

↓

Logout

↓

Destroy Session
```

---

# 4. Session Information

Each session stores

```
Session ID

User ID

Username

Socket

Login Time

Last Activity

Client Address

Status
```

---

# 5. Session States

```
CONNECTED

AUTHENTICATED

ACTIVE

IDLE

DISCONNECTED

EXPIRED

CLOSED
```

---

# 6. Session Manager

The SessionManager controls all active sessions.

Responsibilities

- Create sessions
- Update activity
- Remove sessions
- Find sessions
- Broadcast user status

---

# 7. Session Architecture

```
Authentication Service

↓

Session Manager

↓

Session Repository

↓

PostgreSQL
```

---

# 8. Session Storage

Runtime

```
ConcurrentHashMap<SessionId, Session>
```

Persistent

```
sessions table
```

---

# 9. Session Flow

## Login

```
Receive LOGIN

↓

Validate User

↓

Create Session

↓

Store Session

↓

Return Success
```

---

## Activity

Every request updates

```
lastActivity

heartbeat

status
```

---

## Logout

```
Receive LOGOUT

↓

Close Socket

↓

Remove Session

↓

Update Database

↓

Notify Users
```

---

## Timeout

If

```
Current Time

-

Last Activity

>

Timeout
```

Then

```
Disconnect User

↓

Close Socket

↓

Delete Session
```

---

# 10. Heartbeat

Purpose

Detect lost connections.

```
Server

↓

PING

↓

Client

↓

PONG
```

If no response

```
Close Session
```

---

# 11. Duplicate Login Policy

Current Version

One active session per user.

If a user logs in again

```
Old Session Closed

↓

New Session Created
```

Future versions may support multiple devices.

---

# 12. Security

Sessions should

- Use UUID identifiers
- Never expose passwords
- Expire inactive users
- Validate authentication before executing commands

---

# 13. Session Events

Events include

- Login
- Logout
- Timeout
- Reconnect
- Connection Lost
- Heartbeat Failed

All events should be written to the audit log.

---

# 14. Session Cleanup

A scheduled cleanup task periodically

- Finds expired sessions
- Closes sockets
- Removes memory references
- Updates database
- Logs cleanup results

---

# 15. Future Improvements

- JWT
- Redis-backed sessions
- Clustered session replication
- Multi-device support
- OAuth2 integration

---

# 16. Deliverables

The Session Management subsystem provides

- Authentication state
- Online user tracking
- Session lifecycle management
- Timeout handling
- Heartbeat monitoring
- Secure connection management

It serves as the foundation for user presence, authorization, and reliable communication throughout the chat platform.