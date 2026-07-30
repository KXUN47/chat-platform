# Network Architecture

**Project:** MATLA Chat Platform  
**Version:** 1.0  
**Architecture Style:** Client-Server (TCP)

---

# 1. Overview

The Network Architecture defines how clients communicate with the chat server, how connections are established, managed, monitored, and terminated.

The application uses a **stateful TCP client-server architecture**, where every connected client maintains a persistent socket connection to the server for the duration of the session.

This architecture provides:

- Reliable communication
- Ordered message delivery
- Low latency
- Real-time messaging
- Persistent client sessions

---

# 2. Architecture Overview

```text
                 Internet / LAN
                       │
        ┌──────────────┼──────────────┐
        │              │              │
    Client A       Client B       Client C
        │              │              │
        └──────────────┼──────────────┘
                       │
                TCP Socket Network
                       │
                 ServerSocket
                       │
                Connection Manager
                       │
          ┌────────────┼─────────────┐
          │            │             │
    ClientHandler  ClientHandler ClientHandler
          │            │             │
          └────────────┼─────────────┘
                       │
                Business Services
                       │
                 PostgreSQL Database
```

---

# 3. Network Components

## Client

Responsibilities

- Connect to server
- Authenticate
- Send requests
- Receive responses
- Receive broadcasts
- Upload files
- Download files
- Maintain heartbeat

Main Classes

```text
SocketClient
ConnectionManager
PacketEncoder
PacketDecoder
HeartbeatManager
```

---

## ServerSocket

Responsibilities

- Listen for new connections
- Accept client sockets
- Pass sockets to ConnectionManager

Main Class

```text
SocketServer
```

---

## Connection Manager

Responsibilities

- Register new clients
- Track active sessions
- Remove disconnected users
- Maintain connection pool

Main Class

```text
ConnectionManager
```

---

## Client Handler

Every client receives an independent handler.

Responsibilities

- Read packets
- Decode packets
- Execute commands
- Send responses
- Handle disconnects

Main Class

```text
ClientHandler
```

---

## Business Layer

Responsibilities

- Authentication
- Messaging
- File transfer
- Session management
- User management

---

# 4. Connection Lifecycle

```text
Client Starts
      │
      ▼
Open TCP Socket
      │
      ▼
Connect to Server
      │
      ▼
Authentication
      │
      ▼
Session Created
      │
      ▼
Active Communication
      │
      ▼
Heartbeat Monitoring
      │
      ▼
Logout / Disconnect
      │
      ▼
Socket Closed
```

---

# 5. Network Flow

## Login

```text
Client

↓

TCP Socket

↓

ServerSocket

↓

ClientHandler

↓

Protocol Decoder

↓

AuthenticationService

↓

Database

↓

Login Response
```

---

## Broadcast Message

```text
Client

↓

TCP

↓

Server

↓

Messaging Service

↓

Connection Manager

↓

All Connected Clients
```

---

## Private Message

```text
Client

↓

Messaging Service

↓

Target User

↓

Target Client
```

---

## File Upload

```text
Client

↓

TCP

↓

File Service

↓

Storage

↓

Receiver Notification
```

---

# 6. Threading Model

The server uses a thread pool instead of creating unlimited threads.

```text
Main Thread

↓

ServerSocket

↓

Accept Thread

↓

ExecutorService

↓

ClientHandler

↓

Business Services
```

Recommended

```java
Executors.newFixedThreadPool(100);
```

---

# 7. Session Management

Each connected user has one active session.

Session contains

- Session ID
- User ID
- Username
- Socket
- Login Time
- Last Heartbeat
- Status

States

```text
CONNECTING

↓

AUTHENTICATED

↓

ACTIVE

↓

IDLE

↓

DISCONNECTED
```

---

# 8. Heartbeat Mechanism

Purpose

Detect disconnected clients.

Heartbeat Flow

```text
Server

↓

PING

↓

Client

↓

PONG

↓

Heartbeat Updated
```

Timeout Example

| Event | Value |
|--------|------|
| Ping Interval | 30 seconds |
| Timeout | 90 seconds |

---

# 9. Connection Limits

Initial Version

| Resource | Limit |
|-----------|------:|
| Maximum Clients | 100 |
| Thread Pool | 100 |
| Maximum Packet Size | 64 KB |
| Maximum File Upload | 50 MB |

---

# 10. Network Security

Version 1

- Password hashing
- Input validation
- Packet validation
- Session authentication

Future

- TLS
- Mutual authentication
- Rate limiting
- Encryption

---

# 11. Error Handling

Network errors include

- Connection timeout
- Socket closed
- Invalid packet
- Client disconnect
- Packet corruption

Recovery

- Log error
- Close socket
- Remove session
- Notify remaining users

---

# 12. Logging

Every network event should be logged.

Examples

- Connection established
- Login successful
- Login failed
- Client disconnected
- Heartbeat timeout
- Packet rejected
- File upload started
- File upload completed

---

# 13. Future Enhancements

- TLS/SSL
- Load balancer
- Multiple chat servers
- Distributed sessions
- Kubernetes deployment
- WebSocket gateway
- Mobile gateway

---

# Summary

The network architecture provides:

- Reliable TCP communication
- Persistent client sessions
- Multi-client support
- Thread-safe communication
- Scalable connection handling
- Enterprise-ready foundation
