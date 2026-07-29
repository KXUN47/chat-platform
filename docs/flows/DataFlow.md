# Data Flow

**Project:** MATLA Chat Platform

---

## Request Lifecycle

```text
Client input
  -> packet encoder
  -> TCP connection
  -> server connection handler
  -> protocol decoder and validator
  -> command dispatcher
  -> business service
  -> repository and database
  -> response or event encoder
  -> TCP connection
  -> client
```

## Processing Rules

1. The client assigns a unique request ID and serializes a protocol packet.
2. The server reads the packet, verifies its size and JSON structure, then validates the command schema.
3. The dispatcher checks whether the command requires an authenticated session and routes it to the responsible service.
4. The service applies authorization and business validation before invoking a repository.
5. The repository performs the required database work within a transaction where consistency requires it.
6. The server sends a response with the originating request ID; it sends events for asynchronous notifications such as delivered messages or presence changes.

## Message Delivery

For a broadcast or private message, the messaging service validates the sender, persists the message as required, resolves the recipient sessions, and publishes an event to each connected recipient. If a recipient is offline, the service records the delivery state for later retrieval.

## Failure Flow

```text
Validation, authorization, or infrastructure failure
  -> mapped application exception
  -> protocol error response with request ID
  -> structured server log with correlation ID
```

Protocol error messages must be safe for clients; diagnostics such as stack traces, credentials, and SQL details remain in server logs.

## Related Documents

- [Protocol Specification](../protocol/ProtocolSpecification.md)
- [Exception Architecture](../operations/ExceptionArchitecture.md)
- [Messaging Flow](MessagingFlow.md)
