# Application Modules, Package Structure & Business Services

**Project:** MATLA Chat Platform

**Version:** 1.0

**Language:** Java 21

**Architecture:** Layered Client-Server Architecture

**Build Tool:** Maven Multi-Module

---

# Table of Contents

1. Application Modules
2. Module Dependencies
3. Package Structure
4. Package Responsibilities
5. Business Services
6. Service Interactions
7. Design Principles
8. Future Expansion

---

# 1. Application Modules

The MATLA Chat Platform is organized as a Maven multi-module project. Each module has a single responsibility and can be developed, tested, and maintained independently.

```
chat-platform
│
├── chat-common
├── chat-protocol
├── chat-server
├── chat-client
├── chat-database
├── chat-api
├── chat-web
├── chat-mobile
└── documentation
```

---

# Module Overview

| Module | Responsibility |
|---------|----------------|
| chat-common | Shared models, DTOs, utilities, constants, exceptions |
| chat-protocol | Communication protocol, packets, serialization |
| chat-server | TCP server and business logic |
| chat-client | Desktop client |
| chat-database | Database access |
| chat-api | Future REST API |
| chat-web | Future web application |
| chat-mobile | Future mobile application |
| documentation | Architecture and technical documentation |

---

# 2. chat-common

## Purpose

Contains code shared across every module.

This module has **no business logic**.

---

## Responsibilities

- DTOs
- Enums
- Constants
- Exceptions
- Utility classes
- Validation
- Configuration models

---

## Package Structure

```
chat-common
│
└── com.matlasystems.chat.common
    │
    ├── constants
    ├── dto
    ├── enums
    ├── exception
    ├── model
    ├── util
    ├── validation
    └── configuration
```

---

## Example Classes

```
MessageDTO
LoginRequest
LoginResponse
UserDTO
SessionDTO
Constants
DateUtils
ValidationUtils
ApplicationException
```

---

# 3. chat-protocol

## Purpose

Defines how clients communicate with the server.

The protocol layer is responsible only for communication.

---

## Responsibilities

- Packet definitions
- Encoding
- Decoding
- Command registration
- Serialization
- Protocol validation

---

## Package Structure

```
chat-protocol
│
└── com.matlasystems.chat.protocol
    │
    ├── command
    ├── decoder
    ├── encoder
    ├── handler
    ├── packet
    ├── parser
    ├── registry
    └── serializer
```

---

## Example Classes

```
Packet

PacketEncoder

PacketDecoder

ProtocolParser

CommandRegistry

LoginCommand

MessageCommand

LogoutCommand
```

---

# 4. chat-server

## Purpose

Contains the core application.

This module owns

- networking
- business logic
- repositories
- threading

---

## Responsibilities

- Accept socket connections
- Manage sessions
- Authenticate users
- Process messages
- Store messages
- File transfer
- Logging

---

## Package Structure

```
chat-server
│
└── com.matlasystems.chat.server
    │
    ├── config
    ├── network
    ├── protocol
    ├── controller
    ├── dispatcher
    ├── service
    ├── repository
    ├── security
    ├── session
    ├── thread
    ├── monitoring
    ├── logging
    ├── model
    ├── dto
    ├── exception
    └── util
```

---

## Example Classes

```
ChatServer

ConnectionManager

ClientHandler

CommandDispatcher

AuthenticationService

MessagingService

SessionManager

UserRepository
```

---

# 5. chat-client

## Purpose

Desktop application used by end users.

---

## Responsibilities

- User interface
- Connect to server
- Display messages
- Upload files
- Login
- Notifications

---

## Package Structure

```
chat-client
│
└── com.matlasystems.chat.client
    │
    ├── ui
    ├── controller
    ├── network
    ├── protocol
    ├── service
    ├── model
    ├── util
    └── configuration
```

---

## Example Classes

```
LoginWindow

ChatWindow

SocketClient

ClientController

NotificationService
```

---

# 6. chat-database

## Purpose

Provides persistence support.

---

## Responsibilities

- Database configuration
- SQL execution
- Transactions
- Repository implementations
- Migrations

---

## Package Structure

```
chat-database
│
└── com.matlasystems.chat.database
    │
    ├── datasource
    ├── migration
    ├── repository
    ├── mapper
    ├── entity
    └── transaction
```

---

## Example Classes

```
DatabaseManager

ConnectionFactory

UserRepositoryImpl

MessageRepositoryImpl

TransactionManager
```

---

# 7. chat-api (Future)

## Purpose

Expose REST endpoints.

---

## Responsibilities

- Authentication
- User management
- Messaging API
- File API

---

## Package Structure

```
chat-api
│
└── controller
    ├── auth
    ├── users
    ├── messages
    └── files
```

---

# 8. chat-web (Future)

Future React or Angular application.

---

## Responsibilities

- Web UI
- REST integration
- Authentication

---

# 9. chat-mobile (Future)

Future Android/iOS client.

---

## Responsibilities

- Mobile messaging
- Push notifications
- File upload

---

# 10. documentation

Contains all technical documentation.

```
documentation

architecture

database

protocol

deployment

testing

user-guide
```

---

# Module Dependency Diagram

```
                      chat-client
                           │
                           │
                      chat-protocol
                           │
                           │
                      chat-common
                           │
                           │
                      chat-server
                           │
                    ┌──────┴──────┐
                    │             │
             chat-database     documentation
```

Future

```
chat-api
chat-web
chat-mobile
```

will communicate with

```
chat-server
```

---

# Package Structure

## Server Packages

```
com.matlasystems.chat.server

│
├── config
├── network
├── protocol
├── controller
├── dispatcher
├── service
├── repository
├── security
├── session
├── thread
├── monitoring
├── logging
├── dto
├── model
├── util
└── exception
```

---

## Package Responsibilities

### config

Application configuration.

Example

```
ServerConfiguration

DatabaseConfiguration

ApplicationProperties
```

---

### network

Networking infrastructure.

Example

```
SocketServer

SocketConnection

ConnectionManager

ClientHandler
```

---

### protocol

Protocol processing.

Example

```
PacketReader

PacketWriter

ProtocolProcessor
```

---

### controller

Application controllers.

Example

```
LoginController

MessageController

FileController
```

---

### dispatcher

Routes incoming commands.

Example

```
CommandDispatcher

CommandRegistry
```

---

### service

Contains business logic.

```
AuthenticationService

MessagingService

FileService

UserService

SessionService

NotificationService

AuditService
```

---

### repository

Database abstraction.

```
UserRepository

MessageRepository

SessionRepository

FileRepository
```

---

### security

Authentication and authorization.

```
PasswordHasher

PasswordValidator

PermissionManager
```

---

### session

Session lifecycle.

```
Session

SessionManager

SessionRegistry
```

---

### thread

Thread management.

```
ThreadPoolManager

WorkerThread

CleanupTask
```

---

### monitoring

Server monitoring.

```
PerformanceMonitor

HealthMonitor

MetricsCollector
```

---

### logging

Logging utilities.

```
LoggerFactory

AuditLogger

SystemLogger
```

---

### dto

Data Transfer Objects.

```
LoginRequest

MessageResponse

UserResponse
```

---

### model

Business domain models.

```
User

Session

Message

FileMetadata
```

---

### util

Utility classes.

```
DateUtils

StringUtils

JsonUtils
```

---

### exception

Application exceptions.

```
AuthenticationException

ProtocolException

DatabaseException

NetworkException
```

---

# Business Services

The Business Layer contains all application rules.

Business services never communicate directly with sockets.

Business services never contain SQL.

Business services coordinate repositories and domain logic.

---

# Service Architecture

```
                 Controllers
                      │
                      ▼
             Business Services
                      │
          ┌───────────┼────────────┐
          │           │            │
     Repositories  Security   Session Manager
          │
          ▼
      PostgreSQL
```

---

# AuthenticationService

## Responsibilities

- Register user
- Login
- Logout
- Password validation
- Password hashing
- Session creation

## Public Methods

```
login()

logout()

register()

authenticate()

changePassword()

resetPassword()

validateCredentials()
```

---

# MessagingService

## Responsibilities

- Broadcast messages
- Private messages
- Store messages
- Retrieve history
- Validate recipients

## Public Methods

```
broadcast()

sendPrivate()

saveMessage()

getHistory()

deleteMessage()
```

---

# UserService

## Responsibilities

- Create users
- Update profile
- Online users
- User status
- Last seen

## Public Methods

```
createUser()

findUser()

updateUser()

onlineUsers()

lastSeen()
```

---

# SessionService

## Responsibilities

- Create sessions
- Remove sessions
- Session timeout
- Heartbeat
- Active sessions

## Public Methods

```
create()

destroy()

find()

heartbeat()

isActive()
```

---

# FileService

## Responsibilities

- Upload files
- Download files
- Validate files
- Store metadata

## Public Methods

```
upload()

download()

delete()

validate()

find()
```

---

# NotificationService

## Responsibilities

- Notify users
- Broadcast events
- User online
- User offline

## Public Methods

```
notifyUser()

broadcast()

userOnline()

userOffline()
```

---

# AuditService

## Responsibilities

- Record login attempts
- Record file uploads
- Record messages
- Record administration actions

## Public Methods

```
recordLogin()

recordMessage()

recordUpload()

recordLogout()
```

---

# Service Collaboration Example

## User Login

```
Client

↓

LoginController

↓

AuthenticationService

↓

UserRepository

↓

SessionService

↓

AuditService

↓

Response
```

---

## Send Message

```
Client

↓

MessageController

↓

MessagingService

↓

MessageRepository

↓

NotificationService

↓

Receiver
```

---

## Upload File

```
Client

↓

FileController

↓

FileService

↓

FileRepository

↓

NotificationService
```

---

# Design Principles

Every module and package follows these principles:

- **Single Responsibility Principle (SRP):** Each class or package has one reason to change.
- **Open/Closed Principle (OCP):** Extend functionality without modifying existing code where possible.
- **Dependency Inversion Principle (DIP):** Depend on interfaces rather than concrete implementations.
- **Separation of Concerns:** Networking, protocol handling, business logic, and persistence remain isolated.
- **High Cohesion:** Related functionality is grouped together.
- **Low Coupling:** Modules interact through well-defined interfaces.

---

# Future Expansion

The modular architecture allows the platform to evolve without major restructuring.

Future enhancements include:

- Spring Boot REST API
- JWT Authentication
- WebSocket Gateway
- React Web Client
- Android Client
- iOS Client
- Redis Session Storage
- RabbitMQ Event Messaging
- Docker Deployment
- Kubernetes Orchestration
- Microservices Architecture
- End-to-End Encryption
- Voice and Video Calling
- Presence Service
- Multi-Server Clustering

By keeping responsibilities separated at the module, package, and service levels, the MATLA Chat Platform can grow from a learning project into an enterprise-grade communication platform.
