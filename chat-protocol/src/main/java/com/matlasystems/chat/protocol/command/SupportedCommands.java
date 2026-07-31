package com.matlasystems.chat.protocol.command;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Defines every command supported by the chat protocol.
 */
public final class SupportedCommands {

    private SupportedCommands() {
        throw new IllegalStateException("Utility class");
    }

    /*
     * ============================================================
     * Authentication
     * ============================================================
     */

    public static final String LOGIN = "LOGIN";

    public static final String LOGOUT = "LOGOUT";

    public static final String REGISTER = "REGISTER";

    public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";

    /*
     * ============================================================
     * Messaging
     * ============================================================
     */

    public static final String SEND_MESSAGE = "MESSAGE";

    public static final String PRIVATE_MESSAGE = "PRIVATE_MESSAGE";

    public static final String MESSAGE_HISTORY = "MESSAGE_HISTORY";

    public static final String DELETE_MESSAGE = "DELETE_MESSAGE";

    public static final String EDIT_MESSAGE = "EDIT_MESSAGE";

    /*
     * ============================================================
     * Presence
     * ============================================================
     */

    public static final String USER_LIST = "USER_LIST";

    public static final String USER_INFO = "USER_INFO";

    public static final String USER_ONLINE = "USER_ONLINE";

    public static final String USER_OFFLINE = "USER_OFFLINE";

    /*
     * ============================================================
     * Typing
     * ============================================================
     */

    public static final String TYPING_START = "TYPING_START";

    public static final String TYPING_STOP = "TYPING_STOP";

    /*
     * ============================================================
     * File Transfer
     * ============================================================
     */

    public static final String FILE_UPLOAD = "FILE_UPLOAD";

    public static final String FILE_DOWNLOAD = "FILE_DOWNLOAD";

    public static final String FILE_CANCEL = "FILE_CANCEL";

    /*
     * ============================================================
     * Heartbeat
     * ============================================================
     */

    public static final String HEARTBEAT = "HEARTBEAT";

    public static final String PING = "PING";

    public static final String PONG = "PONG";

    /*
     * ============================================================
     * Server
     * ============================================================
     */

    public static final String SERVER_INFO = "SERVER_INFO";

    public static final String SERVER_STATUS = "SERVER_STATUS";

    /*
     * ============================================================
     * Administration
     * ============================================================
     */

    public static final String KICK_USER = "KICK_USER";

    public static final String SHUTDOWN_SERVER = "SHUTDOWN_SERVER";

    /**
     * Immutable list of every supported command.
     */
    public static final Set<String> ALL =
            Collections.unmodifiableSet(new LinkedHashSet<>(Set.of(

                    LOGIN,
                    LOGOUT,
                    REGISTER,
                    CHANGE_PASSWORD,

                    SEND_MESSAGE,
                    PRIVATE_MESSAGE,
                    MESSAGE_HISTORY,
                    DELETE_MESSAGE,
                    EDIT_MESSAGE,

                    USER_LIST,
                    USER_INFO,
                    USER_ONLINE,
                    USER_OFFLINE,

                    TYPING_START,
                    TYPING_STOP,

                    FILE_UPLOAD,
                    FILE_DOWNLOAD,
                    FILE_CANCEL,

                    HEARTBEAT,
                    PING,
                    PONG,

                    SERVER_INFO,
                    SERVER_STATUS,

                    KICK_USER,
                    SHUTDOWN_SERVER
            )));

}
