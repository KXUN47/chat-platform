# Server Architecture

**Project:** MATLA Chat Platform

---

# 1. Purpose

The Chat Server is the central component of the system.

It is responsible for:

- Accepting client connections
- Authenticating users
- Managing active sessions
- Processing commands
- Routing messages
- Handling file transfers
- Persisting data
- Monitoring server health

---

# 2. High-Level Architecture

```text
                  Clients
                     │
              TCP Socket Layer
                     │
              ServerSocket
                     │
             Connection Manager
                     │
            Protocol Processor
                     │
          Command Dispatcher
                     │
        ┌────────────┼─────────────┐
        │            │             │
 Authentication Messaging File Service
        │            │             │
        └────────────┼─────────────┘
                     │
              Repository Layer
                     │
                PostgreSQL
```

---

# 3. Server Components

## Server Bootstrap

Starts application.

Responsibilities

- Load configuration
- Create thread pool
- Start ServerSocket
- Initialize services

---

## ServerSocket

Responsible for

- Accepting TCP connections
- Creating ClientHandler tasks

---

## Connection Manager

Maintains

- Active sockets
- Connected users
- Heartbeats
- Disconnect cleanup

---

## ClientHandler

One instance per connected client.

Responsibilities

- Read packets
- Decode packets
- Dispatch commands
- Send responses

---

## Protocol Processor

Responsibilities

- Parse requests
- Validate packets
- Convert packets to commands

---

## Command Dispatcher

Routes requests.

Example

```text
LOGIN

↓

LoginCommand

↓

AuthenticationService
```

---

# 4. Business Services

## AuthenticationService

Responsibilities

- Register
- Login
- Logout
- Password hashing
- Session creation

---

## MessagingService

Responsibilities

- Broadcast
- Private messages
- Message history
- Delivery confirmation

---

## UserService

Responsibilities

- User lookup
- Online users
- Status updates
- Last seen

---

## FileService

Responsibilities

- Upload
- Download
- Validation
- Storage

---

## SessionService

Responsibilities

- Create sessions
- Destroy sessions
- Timeout handling

---

# 5. Repository Layer

Repositories abstract database access.

Repositories

- UserRepository
- MessageRepository
- SessionRepository
- FileRepository

Flow

```text
Service

↓

Repository

↓

Database
```

---

# 6. Thread Architecture

```text
Server

↓

Accept Thread

↓

ExecutorService

↓

ClientHandler

↓

Business Services
```

Benefits

- Supports many clients
- Efficient thread reuse
- Better scalability

---

# 7. Package Structure

```text
server

├── bootstrap
├── network
├── protocol
├── command
├── controller
├── service
├── repository
├── security
├── session
├── config
├── thread
├── dto
├── model
├── util
├── exception
└── Main
```

---

# 8. Request Processing Flow

```text
Client

↓

TCP Packet

↓

ClientHandler

↓

Protocol Decoder

↓

Command Dispatcher

↓

Business Service

↓

Repository

↓

Database

↓

Response

↓

Client
```

---

# 9. Session Lifecycle

```text
Client Connects

↓

Login

↓

Authentication

↓

Create Session

↓

Active Session

↓

Logout

↓

Destroy Session
```

---

# 10. Error Handling

Exception hierarchy

```text
ChatException
│
├── AuthenticationException
├── ProtocolException
├── ValidationException
├── FileTransferException
├── DatabaseException
├── NetworkException
└── SessionException
```

All exceptions are:

- Logged
- Converted to error responses
- Sent back to the client when appropriate

---

# 11. Logging

The server logs:

- Startup
- Shutdown
- User logins
- User logouts
- Connections
- Disconnections
- Messages
- File transfers
- Errors
- Performance metrics

Framework

- SLF4J
- Logback

---

# 12. Security

Current security features

- Password hashing
- Input validation
- Session management
- Authentication
- Authorization checks

Future enhancements

- TLS encryption
- JWT
- OAuth2
- Rate limiting
- IP blocking

---

# 13. Deployment

Hosted on an Ubuntu VM.

Directory structure

```text
/opt/chat-server/
/etc/chat/
/var/chat/uploads/
/var/log/chat/
```

Runtime

- Java 21
- PostgreSQL
- Maven-built JAR

---

# 14. Scalability

Current architecture supports:

- Multiple concurrent clients
- Thread pooling
- Layered services
- Modular components

Future scaling options

- Load balancing
- Horizontal scaling
- Distributed messaging
- Redis caching
- Kubernetes

---

# 15. Summary

The server architecture centralizes networking, command processing, business logic, persistence, and monitoring into clearly defined layers and components. This separation of concerns makes the platform easier to test, maintain, and extend while providing a solid foundation for future features such as REST APIs, web clients, mobile clients, and distributed deployments.