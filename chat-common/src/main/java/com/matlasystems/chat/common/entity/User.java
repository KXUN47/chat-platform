package com.matlasystems.chat.common.entity;

import java.time.Instant;
import java.util.Objects;

import com.matlasystems.chat.common.enums.UserStatus;

/**
 * Represents a registered chat user.
 */
public class User {

    private Long userId;
    private String username;
    private String displayName;
    private String email;
    private String passwordHash;
    private UserStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Private constructor used by the Builder.
     */
    private User(Builder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.displayName = builder.displayName;
        this.email = builder.email;
        this.passwordHash = builder.passwordHash;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    /**
     * Returns a new Builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for User.
     */
    public static final class Builder {

        private Long userId;
        private String username;
        private String displayName;
        private String email;
        private String passwordHash;
        private UserStatus status;
        private Instant createdAt;
        private Instant updatedAt;

        private Builder() {
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public Builder status(UserStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public User build() {

            if (status == null) {
                status = UserStatus.OFFLINE;
            }

            Instant now = Instant.now();

            if (createdAt == null) {
                createdAt = now;
            }

            if (updatedAt == null) {
                updatedAt = now;
            }

            return new User(this);
        }
    }

    public boolean isOnline() {
        return status == UserStatus.ONLINE;
    }

    public boolean isOffline() {
        return status == UserStatus.OFFLINE;
    }

    public void changeStatus(UserStatus status) {
        this.status = status;
        this.updatedAt = Instant.now();
    }

    public void updateDisplayName(String displayName) {
        this.displayName = displayName;
        this.updatedAt = Instant.now();
    }

    public void updatePassword(String passwordHash) {
        this.passwordHash = passwordHash;
        this.updatedAt = Instant.now();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof User other)) {
            return false;
        }

        return Objects.equals(userId, other.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", displayName='" + displayName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                '}';
    }
}
