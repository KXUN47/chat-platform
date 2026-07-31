package com.matlasystems.chat.common.constants;

/**
 * Protocol constants used by both
 * client and server.
 */
public final class ProtocolConstants {

    private ProtocolConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
     * Protocol
     */
    public static final String PROTOCOL_NAME =
            "MATLA_CHAT_PROTOCOL";

    public static final String PROTOCOL_VERSION =
            "1.0";

    /*
     * Packet
     */
    public static final String PACKET_TYPE_REQUEST =
            "REQUEST";

    public static final String PACKET_TYPE_RESPONSE =
            "RESPONSE";

    public static final String PACKET_TYPE_EVENT =
            "EVENT";

    /*
     * Authentication
     */
    public static final String LOGIN = "LOGIN";

    public static final String LOGOUT = "LOGOUT";

    public static final String REGISTER = "REGISTER";

    /*
     * Messaging
     */
    public static final String SEND_MESSAGE =
            "MESSAGE";

    public static final String PRIVATE_MESSAGE =
            "PRIVATE_MESSAGE";

    public static final String BROADCAST =
            "BROADCAST";

    /*
     * Users
     */
    public static final String USER_LIST =
            "USER_LIST";

    public static final String USER_CONNECTED =
            "USER_CONNECTED";

    public static final String USER_DISCONNECTED =
            "USER_DISCONNECTED";

    /*
     * File Transfer
     */
    public static final String FILE_UPLOAD =
            "FILE_UPLOAD";

    public static final String FILE_DOWNLOAD =
            "FILE_DOWNLOAD";

    public static final String FILE_CHUNK =
            "FILE_CHUNK";

    public static final String FILE_COMPLETE =
            "FILE_COMPLETE";

    /*
     * Connection
     */
    public static final String CONNECT =
            "CONNECT";

    public static final String DISCONNECT =
            "DISCONNECT";

    public static final String HEARTBEAT =
            "HEARTBEAT";

    public static final String PING =
            "PING";

    public static final String PONG =
            "PONG";

    /*
     * Server
     */
    public static final String SERVER_SHUTDOWN =
            "SERVER_SHUTDOWN";

    public static final String SERVER_STATUS =
            "SERVER_STATUS";

    /*
     * Responses
     */
    public static final String SUCCESS =
            "SUCCESS";

    public static final String ERROR =
            "ERROR";

    public static final String OK =
            "OK";

    public static final String FAILED =
            "FAILED";

    /*
     * JSON Fields
     */
    public static final String FIELD_PACKET_ID =
            "packetId";

    public static final String FIELD_COMMAND =
            "command";

    public static final String FIELD_TIMESTAMP =
            "timestamp";

    public static final String FIELD_PAYLOAD =
            "payload";

    public static final String FIELD_STATUS =
            "status";

    public static final String FIELD_MESSAGE =
            "message";

    public static final String FIELD_DATA =
            "data";

    /*
     * Content Types
     */
    public static final String CONTENT_TYPE_JSON =
            "application/json";

    public static final String CHARSET_UTF8 =
            "UTF-8";

}
