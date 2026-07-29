# Serialization Format

**Project:** MATLA Chat Platform

**Version:** 1.0

---

# 1. Overview

Serialization is the process of converting Java objects into a format suitable for transmission over the network or storage on disk.

For MATLA Chat Platform, the selected serialization format is **JSON (JavaScript Object Notation)**.

---

# 2. Why JSON?

JSON was selected because it is:

- Human-readable
- Lightweight
- Language-independent
- Easy to debug
- Widely supported
- Extensible
- Well-supported by Java libraries (Jackson, Gson)

---

# 3. Alternatives Considered

| Format | Advantages | Disadvantages |
|---------|------------|---------------|
| JSON | Readable, portable | Slightly larger payloads |
| XML | Schema support | Verbose |
| Java Serialization | Simple for Java | Java-only, security concerns |
| Protocol Buffers | Compact, fast | Requires schema generation |
| CBOR | Efficient binary format | Harder to inspect manually |

---

# 4. Serialization Workflow

```text
Java Object
      │
      ▼
Jackson ObjectMapper
      │
      ▼
JSON String
      │
      ▼
UTF-8 Bytes
      │
      ▼
TCP Socket
```

Receiving:

```text
TCP Socket
      │
      ▼
UTF-8 Bytes
      │
      ▼
JSON String
      │
      ▼
Jackson ObjectMapper
      │
      ▼
Java Object
```

---

# 5. Standard Packet Model

```java
Packet
├── id
├── timestamp
├── protocolVersion
├── command
├── status
└── payload
```

---

# 6. Example Java Object

```java
public record LoginRequest(
    String username,
    String password
) {}
```

Serialized JSON

```json
{
  "username":"john",
  "password":"password123"
}
```

---

# 7. Character Encoding

All packets use:

- UTF-8 encoding

Benefits

- Unicode support
- Cross-platform compatibility
- Standard Internet encoding

---

# 8. Packet Framing

TCP is a byte stream and does not preserve message boundaries.

Each JSON packet is prefixed with a 4-byte length field.

```text
+----------------+----------------------+
| Length (4B)    | JSON Payload         |
+----------------+----------------------+
```

Example

```text
00000125
{
  ... JSON ...
}
```

This allows the receiver to determine exactly where each message begins and ends.

---

# 9. Validation Rules

Before deserialization:

- Verify packet length
- Verify UTF-8 encoding
- Reject oversized payloads
- Ensure valid JSON syntax

After deserialization:

- Validate required fields
- Validate command values
- Validate payload schema

---

# 10. File Serialization

Files are not embedded directly in JSON.

JSON is used only for metadata.

Example

```json
{
  "command":"FILE_UPLOAD",
  "payload":{
    "filename":"image.png",
    "size":1048576,
    "recipient":"alice"
  }
}
```

Binary file data is streamed separately over the same TCP connection after the metadata has been acknowledged.

---

# 11. Libraries

Recommended

- Jackson Databind
- Jackson Java Time Module

Maven

```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>

<dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
</dependency>
```

---

# 12. Security Considerations

- Never deserialize arbitrary Java objects.
- Use JSON DTOs only.
- Ignore unknown fields when appropriate for forward compatibility.
- Validate all input before processing.
- Apply maximum payload size limits.

---

# 13. Future Enhancements

Future protocol versions may support:

- GZIP compression
- Protocol Buffers
- CBOR
- Message signing
- End-to-end encrypted payloads

---

# Summary

The serialization strategy provides:

- JSON-based interoperability
- UTF-8 encoding
- Length-prefixed packet framing
- Safe DTO-based serialization
- Efficient debugging
- Forward-compatible protocol evolution
