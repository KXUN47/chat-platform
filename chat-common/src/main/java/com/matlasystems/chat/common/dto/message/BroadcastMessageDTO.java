package com.matlasystems.chat.common.dto.message;

import java.time.Instant;

/**
 * Message delivered to every connected user.
 */
public final class BroadcastMessageDTO extends MessageDTO {

    private static final long serialVersionUID = 1L;

    public BroadcastMessageDTO() {
        super();
    }

    public BroadcastMessageDTO(
            String id,
            String senderId,
            String senderUsername,
            String content,
            Instant sentAt) {

        super(id, senderId, senderUsername, content, sentAt);
    }
}
