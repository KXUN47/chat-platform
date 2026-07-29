# Architectural Pattern

**Project:** MATLA Chat Platform  
**Version:** 1.0  
**Architecture Style:** Layered Client-Server Architecture  
**Language:** Java 21  
**Build Tool:** Maven  
**Deployment:** Ubuntu VM

---

# 1. Purpose

The MATLA Chat Platform follows a **Layered Client-Server Architecture** to promote modularity, maintainability, scalability, and separation of concerns.

The architecture separates networking, business logic, persistence, and presentation into independent layers. Each layer has a single responsibility and communicates only with adjacent layers.

This design allows the system to evolve from a simple TCP chat application into a full enterprise messaging platform supporting desktop, web, mobile, and REST API clients.

---

# 2. Architectural Goals

The architecture is designed to achieve the following goals:

- Loose coupling
- High cohesion
- Scalability
- Maintainability
- Testability
- Reusability
- Extensibility
- Security
- Performance

---

# 3. Architectural Style

The application combines several architectural styles.

## Primary Architecture

- Client-Server Architecture

## Secondary Architecture

- Layered Architecture

## Supporting Patterns

- Repository Pattern
- Command Pattern
- Factory Pattern
- Builder Pattern
- Singleton Pattern
- Strategy Pattern
- Observer Pattern
- Dependency Injection

---

# 4. High-Level System Architecture

```text
                   Users
                      │
     ┌────────────────┼────────────────┐
     │                │                │
 Desktop Client   Desktop Client   Desktop Client
     │                │                │
     └────────────────┼────────────────┘
                      │
                 TCP Socket
                      │
              Chat Server (Java)
                      │
     ┌────────────────┼─────────────────┐
     │                │                 │
Authentication   Messaging      File Transfer
     │                │                 │
     └────────────────┼─────────────────┘
                      │
              Repository Layer
                      │
                 PostgreSQL
```

---

# 5. Layered Architecture

```text
Presentation Layer
        │
        ▼
Network Layer
        │
        ▼
Protocol Layer
        │
        ▼
Application Layer
        │
        ▼
Business Layer
        │
        ▼
Persistence Layer
        │
        ▼
Database
```

---

# 6. Layer Responsibilities

## Presentation Layer

Responsible for:

- User Interface
- Display messages
- User input
- File selection
- Login forms

Examples

- Console UI
- JavaFX UI (future)

---

## Network Layer

Responsible for:

- Socket connections
- Reading packets
- Writing packets
- Reconnect logic
- Heartbeat monitoring

Classes

- SocketServer
- SocketClient
- ClientHandler
- ConnectionManager

---

## Protocol Layer

Responsible for:

- Encoding packets
- Decoding packets
- Packet validation
- Serialization
- Command extraction

Classes

- PacketEncoder
- PacketDecoder
- ProtocolProcessor

---

## Application Layer

Responsible for:

- Request routing
- Command dispatching
- Session coordination
- Response generation

Classes

- CommandDispatcher
- ChatApplication

---

## Business Layer

Responsible for:

- Authentication
- Messaging
- User management
- File management
- Business validation

Services

- AuthenticationService
- MessagingService
- UserService
- FileService
- SessionService

---

## Persistence Layer

Responsible for:

- Database communication
- CRUD operations
- Transactions
- Query execution

Repositories

- UserRepository
- MessageRepository
- SessionRepository
- FileRepository

---

## Database

Stores

- Users
- Messages
- Sessions
- Files
- Audit logs

---

# 7. Data Flow

```text
User

↓

UI

↓

Socket Client

↓

TCP

↓

Socket Server

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

# 8. Design Principles

The project follows SOLID principles.

## Single Responsibility

Each class has one responsibility.

Example

AuthenticationService

Responsible only for authentication.

---

## Open Closed Principle

New commands can be added without modifying existing ones.

Example

Add:

- BlockUserCommand
- RenameCommand

without changing existing commands.

---

## Liskov Substitution

Repositories and services should use interfaces.

Example

UserRepository

↓

JdbcUserRepository

↓

MockUserRepository

---

## Interface Segregation

Small focused interfaces.

Example

AuthenticationService

MessagingService

FileService

---

## Dependency Inversion

High-level modules depend on interfaces instead of implementations.

---

# 9. Quality Attributes

| Attribute | Implementation |
|------------|----------------|
| Scalability | Thread Pool |
| Performance | ExecutorService |
| Reliability | Session Management |
| Security | Password Hashing |
| Maintainability | Layered Architecture |
| Testability | Interfaces |
| Availability | Heartbeat Monitoring |

---

# 10. Future Evolution

The architecture allows future expansion without redesign.

Future additions include:

- Spring Boot REST API
- WebSocket Gateway
- React Frontend
- Android Client
- iOS Client
- Kubernetes
- Microservices
- End-to-End Encryption
- Cloud Deployment

---

# 11. Summary

The MATLA Chat Platform architecture separates networking, business logic, persistence, and presentation into independent layers, providing a maintainable and extensible foundation for an enterprise-grade messaging platform.
