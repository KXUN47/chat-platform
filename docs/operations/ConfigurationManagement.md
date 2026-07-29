# Configuration Management

**Project:** MATLA Chat Platform

---

## Goals

Configuration must be explicit, validated at startup, environment-specific, and free of secrets in source control.

## Configuration Sources

Configuration is resolved in this precedence order, with later sources overriding earlier values:

1. Version-controlled defaults in application resource files.
2. Environment-specific configuration files supplied during deployment.
3. Environment variables.
4. JVM system properties.

Secrets such as database passwords and encryption keys must be injected through environment variables or a secret manager, never committed to the repository.

## Configuration Areas

| Area | Examples |
|---|---|
| Server | Bind address, port, connection limits, timeouts |
| Database | JDBC URL, pool size, credentials, migration settings |
| Protocol | Maximum frame size, supported version, heartbeat interval |
| Files | Storage path, maximum upload size, permitted content types |
| Logging | Log level, output destination, retention policy |

## Startup Validation

The bootstrap process must validate required values, data types, ranges, and dependent settings before opening the server socket. Invalid or missing required configuration fails startup with a clear operator-facing error.

## Operational Practices

- Keep non-secret defaults in `application.properties`-style files.
- Use distinct development, test, and production configurations.
- Record the active profile and non-sensitive effective settings at startup.
- Treat configuration changes as deployment changes unless a setting is explicitly designed for safe reload.
- Restrict file permissions for configuration containing secrets.

## Related Documents

- [Deployment Architecture](../architecture/DeploymentArchitecture.md)
- [Logging Architecture](LoggingArchitecture.md)
