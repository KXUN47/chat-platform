package com.matlasystems.chat.common.entity;

import java.time.Instant;
import java.util.Objects;

import com.matlasystems.chat.common.enums.MessageType;

/**
 * Represents a chat message.
 */
public class Message {

    private Long messageId;

    private Long senderId;

    private Long receiverId;

    private MessageType messageType;

    private String text;

    private Instant sentAt;

    private boolean delivered;

    private boolean read;

    /**
     * Default constructor.
     */
    public Message() {
    }

    /**
     * Private constructor used by the Builder.
     */
    private Message(Builder builder) {
        this.messageId = builder.messageId;
        this.senderId = builder.senderId;
        this.receiverId = builder.receiverId;
        this.messageType = builder.messageType;
        this.text = builder.text;
        this.sentAt = builder.sentAt;
        this.delivered = builder.delivered;
        this.read = builder.read;
    }

    /**
     * Returns a new Builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for Message.
     */
    public static final class Builder {

        private Long messageId;
        private Long senderId;
        private Long receiverId;
        private MessageType messageType;
        private String text;
        private Instant sentAt;
        private boolean delivered;
        private boolean read;

        private Builder() {
        }

        public Builder messageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder senderId(Long senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder receiverId(Long receiverId) {
            this.receiverId = receiverId;
            return this;
        }

        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder sentAt(Instant sentAt) {
            this.sentAt = sentAt;
            return this;
        }

        public Builder delivered(boolean delivered) {
            this.delivered = delivered;
            return this;
        }

        public Builder read(boolean read) {
            this.read = read;
            return this;
        }

        public Message build() {

            if (messageType == null) {
                messageType = MessageType.PRIVATE;
            }

            if (sentAt == null) {
                sentAt = Instant.now();
            }

            return new Message(this);
        }
    }

    /**
     * Marks the message as delivered.
     */
    public void markDelivered() {
        this.delivered = true;
    }

    /**
     * Marks the message as read.
     */
    public void markRead() {
        this.read = true;
    }

    /**
     * Returns whether this is a private message.
     */
    public boolean isPrivate() {
        return messageType == MessageType.PRIVATE;
    }

    /**
     * Returns whether this is a broadcast message.
     */
    public boolean isBroadcast() {
        return messageType == MessageType.BROADCAST;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) {
            return true;
        }

        if (!(object instanceof Message other)) {
            return false;
        }

        return Objects.equals(messageId, other.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", messageType=" + messageType +
                ", sentAt=" + sentAt +
                ", delivered=" + delivered +
                ", read=" + read +
                '}';
    }
}
