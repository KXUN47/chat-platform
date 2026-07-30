package com.matlasystems.chat.common.event.server;
import com.matlasystems.chat.common.event.*;
/** Emitted when the server has stopped. */
public final class ServerStoppedEvent extends ApplicationEvent {
    private final String reason;

    public ServerStoppedEvent(String reason){

        super(EventType.SERVER_STOPPED,
            EventPriority.HIGH,
            "server")
            ;this.reason=reason;
        }

        public String getReason(){
            return reason;
        }
    }
