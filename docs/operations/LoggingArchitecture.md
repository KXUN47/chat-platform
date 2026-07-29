# Logging Architecture

**Project:** MATLA Chat Platform  
**Stack:** SLF4J with Logback

---

## Objectives

Logging provides operational visibility, traceability, and safe diagnostics without exposing sensitive user or system data.

## Log Levels

| Level | Use |
|---|---|
| ERROR | Unhandled failures, unavailable dependencies, and failed operations requiring attention. |
| WARN | Rejected requests, recoverable failures, and unusual but expected conditions. |
| INFO | Startup, shutdown, connection lifecycle, and significant business events. |
| DEBUG | Request-processing detail useful during development or incident investigation. |
| TRACE | Low-level protocol and I/O detail; disabled in normal environments. |

## Required Context

Each server log entry should include timestamp, level, logger, thread, correlation or request ID, connection or session ID when available, and the command name. This context links a client request to its processing and response.

## Log Categories

- `server.lifecycle` for startup, shutdown, and configuration validation.
- `network.connection` for connect, disconnect, timeout, and transport failures.
- `security.authentication` for authentication and authorization outcomes.
- `messaging.delivery` for message acceptance and delivery results.
- `persistence.database` for repository and transaction failures.
- `audit` for security-relevant administrative actions.

## Data Protection and Retention

- Never log passwords, tokens, session secrets, or file contents.
- Mask usernames or identifiers only where required by the deployment's privacy policy.
- Rotate files by date and size, retain them according to the operational policy, and restrict access to operators.
- Emit exceptions once at the boundary where they are handled; avoid duplicate stack traces at every layer.

## Related Documents

- [Exception Architecture](ExceptionArchitecture.md)
- [Configuration Management](ConfigurationManagement.md)
