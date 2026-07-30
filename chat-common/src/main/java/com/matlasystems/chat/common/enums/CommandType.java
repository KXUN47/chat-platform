package com.matlasystems.chat.common.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * Supported protocol commands exchanged between
 * the client and the server.
 *
 * Each command has a protocol value that is sent
 * over the network.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum CommandType {

    /*
     * Connection
     */
    CONNECT("CONNECT"),
    DISCONNECT("DISCONNECT"),

    /*
     * Authentication
     */
    REGISTER("REGISTER"),
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),

    /*
     * Messaging
     */
    MESSAGE("MESSAGE"),
    PRIVATE_MESSAGE("PRIVATE_MESSAGE"),
    BROADCAST("BROADCAST"),

    /*
     * User Operations
     */
    USER_LIST("USER_LIST"),
    USER_CONNECTED("USER_CONNECTED"),
    USER_DISCONNECTED("USER_DISCONNECTED"),

    /*
     * File Transfer
     */
    FILE_UPLOAD("FILE_UPLOAD"),
    FILE_DOWNLOAD("FILE_DOWNLOAD"),
    FILE_CHUNK("FILE_CHUNK"),
    FILE_COMPLETE("FILE_COMPLETE"),

    /*
     * Heartbeat
     */
    HEARTBEAT("HEARTBEAT"),
    PING("PING"),
    PONG("PONG"),

    /*
     * Server
     */
    SERVER_STATUS("SERVER_STATUS"),
    SERVER_SHUTDOWN("SERVER_SHUTDOWN");

    private final String value;

    CommandType(String value) {
        this.value = value;
    }

    /**
     * Returns the protocol value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Finds a command by its protocol value.
     */
    public static Optional<CommandType> fromValue(String value) {

        return Arrays.stream(values())
                .filter(command -> command.value.equalsIgnoreCase(value))
                .findFirst();
    }

    @Override
    public String toString() {
        return value;
    }

}
