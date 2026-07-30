package com.matlasystems.chat.common.constants;

/**
 * File upload/download constants.
 */
public final class FileConstants {

    private FileConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    /*
     * Directories
     */
    public static final String ROOT_DIRECTORY =
            "/var/chat";

    public static final String UPLOAD_DIRECTORY =
            "/var/chat/uploads";

    public static final String DOWNLOAD_DIRECTORY =
            "/var/chat/downloads";

    public static final String TEMP_DIRECTORY =
            "/var/chat/temp";

    /*
     * File Limits
     */
    public static final long MAX_FILE_SIZE =
            100L * 1024L * 1024L; //100 MB

    public static final int MAX_FILENAME_LENGTH = 255;

    /*
     * File Transfer
     */
    public static final int DEFAULT_CHUNK_SIZE = 8192;

    public static final int DEFAULT_BUFFER_SIZE = 8192;

    /*
     * Supported Extensions
     */
    public static final String TXT = ".txt";

    public static final String PDF = ".pdf";

    public static final String DOC = ".doc";

    public static final String DOCX = ".docx";

    public static final String XLS = ".xls";

    public static final String XLSX = ".xlsx";

    public static final String CSV = ".csv";

    public static final String JSON = ".json";

    public static final String XML = ".xml";

    public static final String ZIP = ".zip";

    public static final String RAR = ".rar";

    public static final String PNG = ".png";

    public static final String JPG = ".jpg";

    public static final String JPEG = ".jpeg";

    public static final String GIF = ".gif";

    public static final String BMP = ".bmp";

    public static final String MP3 = ".mp3";

    public static final String MP4 = ".mp4";

    /*
     * MIME Types
     */
    public static final String MIME_TEXT =
            "text/plain";

    public static final String MIME_JSON =
            "application/json";

    public static final String MIME_XML =
            "application/xml";

    public static final String MIME_PDF =
            "application/pdf";

    public static final String MIME_ZIP =
            "application/zip";

    public static final String MIME_JPEG =
            "image/jpeg";

    public static final String MIME_PNG =
            "image/png";

    public static final String MIME_GIF =
            "image/gif";

    public static final String MIME_MP4 =
            "video/mp4";

    public static final String MIME_MP3 =
            "audio/mpeg";

    /*
     * File Status
     */
    public static final String STATUS_PENDING =
            "PENDING";

    public static final String STATUS_UPLOADING =
            "UPLOADING";

    public static final String STATUS_COMPLETED =
            "COMPLETED";

    public static final String STATUS_FAILED =
            "FAILED";

    public static final String STATUS_CANCELLED =
            "CANCELLED";

    /*
     * Checksum Algorithms
     */
    public static final String SHA_256 = "SHA-256";

    public static final String SHA_512 = "SHA-512";

}
