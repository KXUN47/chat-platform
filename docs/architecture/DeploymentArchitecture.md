# Deployment Architecture

**Project:** MATLA Chat Platform

**Version:** 1.0

---

# Purpose

This document defines how the Chat Platform is deployed to an Ubuntu Virtual Machine.

Deployment includes

- Server
- Database
- Logs
- Uploaded Files
- Configuration
- Monitoring

---

# Deployment Environment

Operating System

```

Ubuntu Server 24.04 LTS

```

Java

```

Java 21 LTS

```

Database

```

PostgreSQL

```

Build Tool

```

Maven

```

Version Control

```

Git

GitHub

```

---

# Deployment Architecture

```

Internet

│

Desktop Clients

│

TCP Socket

│

Ubuntu VM

│

Java Chat Server

│

Business Services

│

Repositories

│

PostgreSQL

│

Storage

```

---

# Server Directory Structure

```

/opt/chat-server/

/opt/chat-client/

/etc/chat/

/var/chat/uploads/

/var/chat/temp/

/var/log/chat/

/var/backups/chat/

```

Purpose

| Directory | Purpose |
|------------|----------|
| /opt/chat-server | Server binaries |
| /etc/chat | Configuration |
| /var/chat/uploads | Uploaded files |
| /var/chat/temp | Temporary storage |
| /var/log/chat | Application logs |
| /var/backups/chat | Database backups |

---

# Application Configuration

```

application.properties

```

Example

```properties
server.host=0.0.0.0
server.port=9000

database.url=jdbc:postgresql://localhost:5432/chat_platform
database.username=chat
database.password=********

uploads.directory=/var/chat/uploads

thread.pool.size=100

heartbeat.interval=30000
```

---

# Build Process

```

Git Clone

↓

Maven Build

↓

Jar File

↓

Deployment

↓

Server Start

```

Command

```

mvn clean package

```

Produces

```

chat-server.jar

```

---

# Deployment Process

```

Developer

↓

Git Commit

↓

GitHub

↓

Ubuntu VM

↓

Git Pull

↓

Maven Package

↓

Restart Server

```

Example

```

git pull

mvn clean package

java -jar target/chat-server.jar

```

---

# Startup Sequence

```

Application Start

↓

Load Configuration

↓

Initialize Logging

↓

Create Thread Pool

↓

Connect PostgreSQL

↓

Open Server Socket

↓

Accept Client Connections

```

---

# Logging

Location

```

/var/log/chat/

```

Files

```

server.log

application.log

error.log

security.log

```

---

# Database Backup

Nightly backup

```

pg_dump

↓

Compressed Backup

↓

/var/backups/chat

```

Retention

- Daily
- Weekly
- Monthly

---

# Monitoring

Monitor

- CPU
- Memory
- Disk Usage
- Network
- Active Connections
- JVM Heap
- Thread Count
- Database Status

Future

- Prometheus
- Grafana

---

# Failure Recovery

Application Crash

```

Restart Service

↓

Recover Sessions

↓

Accept New Clients

```

Database Failure

```

Reconnect

↓

Log Error

↓

Retry

```

---

# Future Deployment

Version 2

```

Docker

↓

Docker Compose

↓

NGINX

↓

PostgreSQL

```

Version 3

```

Kubernetes

↓

Load Balancer

↓

Multiple Chat Servers

↓

Shared Database

```

---

# Deployment Checklist

- Ubuntu Server installed
- Java 21 installed
- PostgreSQL installed
- Git installed
- Maven installed
- Firewall configured
- Application built
- Configuration verified
- Database initialized
- Upload directory created
- Log directory created
- Backups configured
- Server started successfully

---

# Summary

The initial deployment targets a single Ubuntu VM with a Java application and a local PostgreSQL database. The architecture is intentionally simple for development and learning while following production-oriented practices such as externalized configuration, structured logging, backups, and monitoring. It also provides a clear migration path to Docker, reverse proxies, and Kubernetes as the platform grows.
