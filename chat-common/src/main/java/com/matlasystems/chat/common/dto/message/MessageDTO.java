package com.matlasystems.chat.common.dto.message;

import java.io.Serializable;
import java.time.Instant;

/** Base message payload shared by broadcast and private messages. */
public class MessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String senderId;
    private String senderUsername;
    private String content;
    private Instant sentAt;
    public MessageDTO() { }
    public MessageDTO(String id, String senderId, String senderUsername, String content, Instant sentAt) { this.id = id; this.senderId = senderId; this.senderUsername = senderUsername; this.content = content; this.sentAt = sentAt; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSenderId() { return senderId; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public String getSenderUsername() { return senderUsername; }
    public void setSenderUsername(String senderUsername) { this.senderUsername = senderUsername; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Instant getSentAt() { return sentAt; }
    public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
}
