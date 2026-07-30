package com.matlasystems.chat.common.constants;

/**
 * Global application constants.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public final class ApplicationConstants {

    private ApplicationConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
     * Application Information
     */
    public static final String APPLICATION_NAME = "MATLA Chat Platform";

    public static final String APPLICATION_SHORT_NAME = "MATLA Chat";

    public static final String ORGANIZATION = "MATLA Systems";

    public static final String VERSION = "1.0.0";

    public static final String BUILD_NUMBER = "1000";

    public static final String ENVIRONMENT = "Development";

    /*
     * Encoding
     */
    public static final String DEFAULT_CHARSET = "UTF-8";

    /*
     * Time
     */
    public static final String DEFAULT_TIMEZONE = "UTC";

    /*
     * Date Format
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String DATE_TIME_FORMAT =
            "yyyy-MM-dd HH:mm:ss";

    public static final String ISO_DATE_TIME =
            "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /*
     * Pagination Defaults
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final int MAX_PAGE_SIZE = 100;

    /*
     * File Upload
     */
    public static final long MAX_FILE_SIZE =
            100L * 1024L * 1024L; //100 MB

    public static final int MAX_FILENAME_LENGTH = 255;

    /*
     * User
     */
    public static final int MIN_USERNAME_LENGTH = 3;

    public static final int MAX_USERNAME_LENGTH = 32;

    public static final int MIN_PASSWORD_LENGTH = 8;

    public static final int MAX_PASSWORD_LENGTH = 128;

    /*
     * Message
     */
    public static final int MAX_MESSAGE_LENGTH = 5000;

    /*
     * Logging
     */
    public static final String LOG_DIRECTORY = "logs";

    public static final String SERVER_LOG = "server.log";

    public static final String ERROR_LOG = "error.log";

    public static final String SECURITY_LOG = "security.log";

    /*
     * Upload Directory
     */
    public static final String UPLOAD_DIRECTORY =
            "/var/chat/uploads";

}
