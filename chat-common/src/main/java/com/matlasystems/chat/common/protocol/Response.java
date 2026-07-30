package com.matlasystems.chat.common.protocol;

import java.io.Serial;
import java.io.Serializable;

import com.matlasystems.chat.common.enums.CommandType;

/**
 * Packet returned after a request has been processed.
 */
public class Response extends Packet {

    @Serial
    private static final long serialVersionUID = 1L;

    public Response() {
        super();
    }

    public Response(CommandType command, Serializable payload) {
        super(Header.create(command, PacketStatus.RESPONSE), payload);
    }

    public Response(Header header, Serializable payload) {
        super(header, payload);
    }

    public boolean isSuccessful() {
        return isResponse();
    }
}
