# Project Scope

**Project:** MATLA Chat Platform

**Version:** 1.0

---

# Table of Contents

1. Introduction
2. Purpose
3. Project Objectives
4. In Scope
5. Out of Scope
6. Assumptions
7. Constraints
8. Future Enhancements
9. Success Metrics
10. Summary

---

# 1. Introduction

Project scope defines what will and will not be developed during the first release of the MATLA Chat Platform.

A clearly defined scope helps prevent:

- Scope creep
- Delays
- Budget overruns
- Unnecessary complexity

---

# 2. Purpose

The first release focuses on building an enterprise-grade TCP chat application that demonstrates software engineering principles rather than competing with commercial messaging platforms.

---

# 3. Project Objectives

The system shall:

- Support multiple simultaneous users
- Enable real-time messaging
- Demonstrate TCP networking
- Demonstrate multithreading
- Store messages
- Support authentication
- Support file transfers
- Be deployable on Ubuntu

---

# 4. In Scope

## Authentication

- User registration
- User login
- Logout
- Password hashing
- Session management

---

## Networking

- TCP sockets
- Client-server communication
- Packet serialization
- Connection management
- Heartbeats

---

## Messaging

- Broadcast messaging
- Private messaging
- Message history
- Message timestamps

---

## User Management

- Online users
- User status
- Session tracking

---

## File Transfer

- Upload files
- Download files
- File metadata
- Progress tracking

---

## Database

- User storage
- Session storage
- Message history
- File metadata

---

## Logging

- Server logs
- Connection logs
- Error logs
- Audit logs

---

## Testing

- Unit tests
- Integration tests
- Load testing

---

## Deployment

- Ubuntu VM
- Java 21
- Maven
- PostgreSQL

---

# 5. Out of Scope

The following features are intentionally excluded from Version 1.

## Communication

- Video calls
- Voice calls
- Screen sharing
- Group voice chat

---

## Advanced Messaging

- Emoji reactions
- Message editing
- Message deletion
- Polls
- Stickers

---

## Mobile

- Android application
- iOS application

---

## Web

- Browser client
- REST API
- WebSockets

---

## Security

- End-to-end encryption
- Multi-factor authentication
- OAuth

---

## Infrastructure

- Kubernetes
- Docker Swarm
- Load balancing
- Distributed messaging

---

## AI

- AI assistant
- Chatbot
- Message summarization

---

# 6. Assumptions

- Java 21 is available.
- PostgreSQL is installed.
- Ubuntu Server hosts the application.
- Users have stable TCP connectivity.
- Git is used for version control.

---

# 7. Constraints

- Java only
- TCP protocol
- Client-server architecture
- Maven build system
- Ubuntu deployment
- PostgreSQL database

---

# 8. Future Enhancements

Version 2

- Spring Boot REST API
- React Web Client
- JWT Authentication
- WebSocket Gateway

Version 3

- Android Client
- iOS Client
- Push Notifications
- End-to-End Encryption

Version 4

- Microservices
- Kubernetes
- Distributed Messaging
- High Availability

---

# 9. Success Metrics

The project is considered successful if it can:

- Support 100+ concurrent users
- Deliver messages reliably
- Authenticate users securely
- Transfer files successfully
- Recover from client disconnects
- Pass automated tests
- Deploy successfully on Ubuntu

---

# 10. Summary

Version 1 focuses on creating a robust and maintainable networking platform. Advanced collaboration, cloud-native infrastructure, and modern communication features are intentionally deferred to future releases to maintain a manageable scope and ensure high software quality.