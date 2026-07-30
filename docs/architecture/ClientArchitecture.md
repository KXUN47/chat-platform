# Client Architecture

**Project:** MATLA Chat Platform

---

# 1. Purpose

The Client Application provides the user interface and manages communication with the chat server.

Its responsibilities include:

- Connecting to the server
- User authentication
- Sending requests
- Receiving responses
- Displaying information
- Uploading files
- Maintaining the user session

The client contains no business logic. All business rules execute on the server.

---

# 2. Client Architecture Overview

```text
+--------------------------------------+
|              User                    |
+------------------+-------------------+
                   │
                   ▼
+--------------------------------------+
|         Presentation Layer           |
|        Console / JavaFX UI           |
+------------------+-------------------+
                   │
                   ▼
+--------------------------------------+
|        Client Controller             |
+------------------+-------------------+
                   │
                   ▼
+--------------------------------------+
|        Client Services               |
+------------------+-------------------+
                   │
                   ▼
+--------------------------------------+
|      Protocol Encoder/Decoder        |
+------------------+-------------------+
                   │
                   ▼
+--------------------------------------+
|       Socket Manager                 |
+------------------+-------------------+
                   │
                   ▼
              TCP Connection
                   │
                   ▼
               Chat Server
```

---

# 3. Client Components

## Presentation Layer

Responsibilities

- Login screen
- Chat window
- Online users
- File upload
- Message display

Possible implementations

- Console
- JavaFX

---

## Client Controller

Coordinates all user interactions.

Responsibilities

- Login
- Logout
- Send message
- Upload file
- Display notifications

Example

```text
User clicks Send

↓

Controller

↓

MessagingService

↓

SocketManager
```

---

## Client Services

Services available on the client.

### AuthenticationClientService

- Login
- Logout

### MessagingClientService

- Send broadcast
- Send private message

### FileClientService

- Upload
- Download

### UserClientService

- Refresh online users

---

## Protocol Layer

Responsible for converting Java objects into packets.

Example

```text
LoginRequest

↓

JSON

↓

Socket
```

Incoming

```text
JSON

↓

Java Object

↓

Controller
```

---

## Socket Manager

Responsibilities

- Connect
- Disconnect
- Reconnect
- Heartbeat
- Send packet
- Receive packet

---

# 4. Session Flow

```text
Application Start

↓

Connect

↓

Login

↓

Receive Session

↓

Chat

↓

Logout

↓

Disconnect
```

---

# 5. Client Package Structure

```text
client

├── ui
├── controller
├── service
├── network
├── protocol
├── dto
├── model
├── config
├── util
├── exception
└── Main
```

---

# 6. Major Classes

## Main

Starts application.

---

## ClientController

Coordinates UI.

---

## SocketManager

Maintains TCP connection.

---

## PacketEncoder

Serializes requests.

---

## PacketDecoder

Deserializes responses.

---

## ClientSession

Stores

- User ID
- Username
- Session ID
- Login Time

---

# 7. Client State Diagram

```text
Disconnected

↓

Connecting

↓

Connected

↓

Authenticating

↓

Authenticated

↓

Chatting

↓

Disconnected
```

---

# 8. Responsibilities Summary

| Component | Responsibility |
|-----------|----------------|
| UI | User interaction |
| Controller | Coordinate actions |
| Services | Client operations |
| Socket Manager | TCP communication |
| Protocol | Packet serialization |
| Session | Store logged-in user |

---

# 9. Design Principles

- Thin Client
- Server-Centric Business Logic
- Stateless Requests
- Single Responsibility
- Layered Architecture
- Reusable Components

---

# 10. Future Improvements

- JavaFX UI
- Dark mode
- Push notifications
- Voice calls
- Video calls
- Web Client
- Mobile Client
