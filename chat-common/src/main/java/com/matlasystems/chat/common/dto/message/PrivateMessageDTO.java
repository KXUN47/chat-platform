package com.matlasystems.chat.common.dto.message;

import java.time.Instant;

/** Message addressed to one recipient. */
public final class PrivateMessageDTO extends MessageDTO {
    private static final long serialVersionUID = 1L;
    private String recipientId;
    public PrivateMessageDTO() { }
    public PrivateMessageDTO(String id, String senderId, String senderUsername, String recipientId, String content, Instant sentAt) { super(id, senderId, senderUsername, content, sentAt); this.recipientId = recipientId; }
    public String getRecipientId() { return recipientId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
}
