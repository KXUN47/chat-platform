# RepositoryArchitecture.md

# Repository Layer Architecture

**Project:** MATLA Chat Platform

**Version:** 1.0

**Technology Stack**

- Java 21
- PostgreSQL
- JDBC
- Maven
- Ubuntu Server

---

# 1. Overview

The Repository Layer is responsible for interacting with the database.

Its primary responsibility is to isolate all database access from the rest of the application.

The Service Layer should never execute SQL directly.

Instead, services communicate with repositories, and repositories communicate with PostgreSQL.

```
Presentation Layer
        │
Business Services
        │
Repository Layer
        │
 JDBC
        │
 PostgreSQL
```

---

# 2. Objectives

The Repository Layer should:

- Encapsulate SQL queries
- Hide database implementation details
- Separate business logic from persistence
- Improve maintainability
- Improve testability
- Reduce duplicate SQL code

---

# 3. Responsibilities

Repositories are responsible for:

- CRUD Operations
- Transactions
- Query Execution
- Object Mapping
- Database Connection Management
- Error Handling

Repositories should NOT:

- Validate business rules
- Perform authentication
- Process protocol commands
- Execute socket operations

---

# 4. Architecture

```
Client

↓

TCP Server

↓

Command Dispatcher

↓

Authentication Service

↓

User Repository

↓

PostgreSQL
```

---

# 5. Repository Structure

```
repository/

    Repository.java

    UserRepository.java

    SessionRepository.java

    MessageRepository.java

    FileRepository.java

    AuditRepository.java

    DatabaseManager.java

    mapper/

        UserMapper.java

        MessageMapper.java

        SessionMapper.java

        FileMapper.java
```

---

# 6. Generic Repository Interface

Each repository should implement common operations.

Example methods

```
save()

update()

delete()

findById()

findAll()

exists()
```

---

# 7. User Repository

Responsibilities

- Create users
- Find users
- Update profile
- Update password
- Update status

Example Methods

```
createUser()

findById()

findByUsername()

findByEmail()

updateUser()

updateStatus()

deleteUser()

listOnlineUsers()
```

---

# 8. Session Repository

Responsibilities

- Store active sessions
- Update heartbeat
- Logout
- Session timeout

Methods

```
createSession()

findSession()

findByUser()

updateHeartbeat()

closeSession()

deleteExpiredSessions()
```

---

# 9. Message Repository

Responsibilities

- Store messages
- Retrieve history
- Search messages

Methods

```
saveMessage()

findConversation()

findMessages()

findRecentMessages()

deleteMessage()
```

---

# 10. File Repository

Responsibilities

- Store metadata
- Retrieve uploads
- Delete uploads

Methods

```
saveFile()

findFile()

deleteFile()

listFiles()
```

---

# 11. Audit Repository

Responsibilities

Store important events.

Examples

```
Login

Logout

Failed Login

Message Sent

Upload

Download

Server Start

Server Stop
```

---

# 12. Database Manager

Purpose

Centralize database connectivity.

Responsibilities

- Open connection
- Close connection
- Transaction management
- Connection pooling (future)

Methods

```
getConnection()

beginTransaction()

commit()

rollback()

close()
```

---

# 13. Object Mapping

Repositories should never expose ResultSet.

Instead

```
ResultSet

↓

Mapper

↓

Domain Object
```

Example

```
UserMapper

↓

User
```

---

# 14. Error Handling

Repositories throw repository-specific exceptions.

```
RepositoryException

↓

UserRepositoryException

MessageRepositoryException

SessionRepositoryException
```

---

# 15. Transaction Flow

```
Service

↓

Repository

↓

SQL

↓

Commit

↓

Return
```

If an error occurs

```
Rollback

↓

Throw Exception
```

---

# 16. Best Practices

✔ Never concatenate SQL

✔ Use PreparedStatement

✔ Close ResultSet

✔ Close Statement

✔ Use try-with-resources

✔ Log SQL failures

✔ Keep repositories small

✔ One repository per aggregate

---

# 17. Future Improvements

- HikariCP
- JPA/Hibernate
- Flyway
- Liquibase
- Repository Interfaces
- Unit Tests using TestContainers

---

# 18. Deliverables

Repository Layer

- UserRepository
- SessionRepository
- MessageRepository
- FileRepository
- AuditRepository
- DatabaseManager
- Mappers

The Repository Layer provides a clean abstraction between the business logic and the PostgreSQL database.
