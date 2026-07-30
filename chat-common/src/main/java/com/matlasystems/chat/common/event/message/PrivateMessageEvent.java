package com.matlasystems.chat.common.event.message;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a private message is sent from one user to another.
 */
public final class PrivateMessageEvent
        extends ApplicationEvent {

    private final Long messageId;

    private final Long senderId;

    private final Long recipientId;

    /**
     * Creates a new private message event.
     *
     * @param messageId message identifier
     * @param senderId sender identifier
     * @param recipientId recipient identifier
     */
    public PrivateMessageEvent(
            Long messageId,
            Long senderId,
            Long recipientId) {

        super(
                EventType.PRIVATE_MESSAGE,
                EventPriority.NORMAL,
                "message");

        this.messageId = messageId;
        this.senderId = senderId;
        this.recipientId = recipientId;
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
     * Returns the recipient identifier.
     *
     * @return recipient ID
     */
    public Long getRecipientId() {
        return recipientId;
    }

}
