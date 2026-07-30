package com.matlasystems.chat.common.event.message;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a message is broadcast to all connected users.
 */
public final class BroadcastMessageEvent
        extends ApplicationEvent {

    private final Long messageId;

    private final Long senderId;

    /**
     * Creates a new broadcast message event.
     *
     * @param messageId message identifier
     * @param senderId sender identifier
     */
    public BroadcastMessageEvent(
            Long messageId,
            Long senderId) {

        super(
                EventType.BROADCAST_MESSAGE,
                EventPriority.NORMAL,
                "message");

        this.messageId = messageId;
        this.senderId = senderId;
    }

    /**
     * Returns the message identifier.
     *
     * @return message ID
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * Returns the sender identifier.
     *
     * @return sender ID
     */
    public Long getSenderId() {
        return senderId;
    }

}
