# Exception Architecture

**Project:** MATLA Chat Platform

---

## Purpose

The exception architecture separates expected client and business failures from infrastructure failures, so the protocol returns consistent error packets while operators receive actionable diagnostics.

## Exception Categories

| Category | Examples | Client outcome |
|---|---|---|
| Validation | Invalid packet, empty message, invalid file metadata | `ERROR` packet with a stable validation code |
| Authentication and authorization | Invalid credentials, expired session, forbidden command | `ERROR` packet without security details |
| Domain | Recipient unavailable, duplicate username, unsupported action | `ERROR` packet with domain-specific code |
| Infrastructure | Database unavailable, storage failure, socket I/O failure | Generic internal error; detailed cause is logged |

## Handling Boundaries

- Protocol decoding converts malformed input into protocol-validation exceptions.
- Services throw domain exceptions for rule violations and do not construct protocol packets.
- Repositories translate database-specific exceptions into persistence exceptions.
- The connection or command boundary maps known exceptions to protocol error packets and logs unexpected failures with request context.

## Error Response Rules

- Include the originating request ID, command, stable error code, and safe message.
- Do not expose stack traces, SQL statements, hostnames, or secrets to clients.
- Preserve the original exception as the cause when translating exceptions.
- Treat a malformed or oversized packet as a connection-level failure when the protocol cannot safely recover.

## Suggested Base Types

```text
ChatException
|-- ValidationException
|-- AuthenticationException
|-- AuthorizationException
|-- DomainException
`-- InfrastructureException
    |-- PersistenceException
    `-- FileStorageException
```

## Related Documents

- [Protocol Specification](../protocol/ProtocolSpecification.md)
- [Logging Architecture](LoggingArchitecture.md)
- [Data Flow](../flows/DataFlow.md)
