package com.matlasystems.chat.common.constants;

/**
 * Standard success messages returned to users and clients.
 */
public final class SuccessMessages {

    private SuccessMessages() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String REGISTRATION_SUCCESSFUL = "Registration successful";
    public static final String LOGIN_SUCCESSFUL = "Login successful";
    public static final String LOGOUT_SUCCESSFUL = "Logout successful";
    public static final String MESSAGE_SENT = "Message sent successfully";
    public static final String MESSAGE_DELIVERED = "Message delivered successfully";
    public static final String FILE_UPLOAD_STARTED = "File upload started";
    public static final String FILE_UPLOAD_COMPLETED = "File upload completed successfully";
    public static final String FILE_DOWNLOAD_READY = "File is ready for download";
    public static final String USER_LIST_RETRIEVED = "User list retrieved successfully";
    public static final String MESSAGE_HISTORY_RETRIEVED = "Message history retrieved successfully";
    public static final String SERVER_STARTED = "Server started successfully";
    public static final String SERVER_STOPPED = "Server stopped successfully";
}
