package com.matlasystems.chat.common.protocol;

/** Identifies the role of a packet in the request-response protocol. */
public enum PacketStatus {
    REQUEST,
    RESPONSE,
    EVENT,
    ERROR;

    public boolean isRequest() { return this == REQUEST; }
    public boolean isResponse() { return this == RESPONSE; }
    public boolean isEvent() { return this == EVENT; }
    public boolean isError() { return this == ERROR; }
}
