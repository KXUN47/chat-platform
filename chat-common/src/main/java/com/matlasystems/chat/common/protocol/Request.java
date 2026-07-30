package com.matlasystems.chat.common.protocol;

import java.io.Serial;
import java.io.Serializable;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Packet sent to request an operation from the server.
 */
public class Request extends Packet {

    @Serial
    private static final long serialVersionUID = 1L;

    public Request() {
        super();
    }

    public Request(CommandType command, Serializable payload) {
        super(Header.create(command, PacketStatus.REQUEST), payload);
    }

    public Request(Header header, Serializable payload) {
        super(header, payload);

        if (header != null) {
            header.setStatus(PacketStatus.REQUEST);
        }
    }
}
