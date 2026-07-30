package com.matlasystems.chat.common.event.message;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a message is accepted for delivery.
 */
public final class MessageSentEvent
        extends ApplicationEvent {

    private final Long messageId;

    private final Long senderId;

    private final Long receiverId;

    /**
     * Creates a new message sent event.
     *
     * @param messageId message identifier
     * @param senderId sender identifier
     * @param receiverId receiver identifier
     */
    public MessageSentEvent(
            Long messageId,
            Long senderId,
            Long receiverId) {

        super(
                EventType.MESSAGE_SENT,
                EventPriority.NORMAL,
                "message");

        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
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

    /**
     * Returns the receiver identifier.
     *
     * @return receiver ID
     */
    public Long getReceiverId() {
        return receiverId;
    }

}
