package com.matlasystems.chat.common.event.message;

import com.matlasystems.chat.common.event.ApplicationEvent;
import com.matlasystems.chat.common.event.EventPriority;
import com.matlasystems.chat.common.event.EventType;

/**
 * Emitted when a message is delivered to a recipient.
 */
public final class MessageDeliveredEvent
        extends ApplicationEvent {

    private final Long messageId;

    private final Long recipientId;

    /**
     * Creates a new message delivered event.
     *
     * @param messageId message identifier
     * @param recipientId recipient identifier
     */
    public MessageDeliveredEvent(
            Long messageId,
            Long recipientId) {

        super(
                EventType.MESSAGE_DELIVERED,
                EventPriority.NORMAL,
                "message");

        this.messageId = messageId;
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
     * Returns the recipient identifier.
     *
     * @return recipient ID
     */
    public Long getRecipientId() {
        return recipientId;
    }

}
