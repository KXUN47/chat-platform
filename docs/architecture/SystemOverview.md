# System Overview

**Project:** MATLA Chat Platform  
**Architecture:** Layered TCP client-server application

---

## Purpose

MATLA Chat Platform provides authenticated, real-time messaging and file-transfer capabilities over TCP. It is structured so that transport, protocol processing, business rules, and persistence can evolve independently.

## System Context

```text
Desktop Client
      |
      | TCP + JSON protocol
      v
Chat Server
  |-- Connection and session management
  |-- Command dispatch and business services
  |-- Repositories
      |
      v
PostgreSQL
```

## Core Components

| Component | Responsibility |
|---|---|
| Client | Collect user input, maintain the socket connection, and render server responses and events. |
| Network layer | Accept connections, read and write frames, and manage connection lifecycle. |
| Protocol layer | Decode, validate, and encode versioned packets. |
| Application layer | Route commands and coordinate requests, sessions, and responses. |
| Business services | Apply authentication, messaging, user, and file-transfer rules. |
| Persistence layer | Encapsulate transactions and PostgreSQL access through repositories. |

## Architectural Boundaries

- Clients communicate with the server only through the protocol contract.
- The network layer does not contain business rules or SQL.
- Services depend on repository interfaces, not database implementations.
- Repositories do not expose database details to protocol or presentation code.

## Related Documents

- [Architectural Pattern](ArchitecturalPattern.md)
- [Server Architecture](ServerArchitecture.md)
- [Protocol Specification](../protocol/ProtocolSpecification.md)
- [Data Flow](../flows/DataFlow.md)
