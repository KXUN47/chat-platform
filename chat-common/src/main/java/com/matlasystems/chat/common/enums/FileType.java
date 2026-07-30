package com.matlasystems.chat.common.enums;

/**
 * Supported uploaded file categories.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public enum FileType {

    IMAGE,

    VIDEO,

    AUDIO,

    DOCUMENT,

    ARCHIVE,

    EXECUTABLE,

    SPREADSHEET,

    PRESENTATION,

    PDF,

    TEXT,

    SOURCE_CODE,

    OTHER;

    /**
     * Returns true if the file is media.
     */
    public boolean isMedia() {

        return this == IMAGE
                || this == VIDEO
                || this == AUDIO;
    }

    /**
     * Returns true if the file is a document.
     */
    public boolean isDocument() {

        return this == DOCUMENT
                || this == PDF
                || this == SPREADSHEET
                || this == PRESENTATION
                || this == TEXT;
    }

    /**
     * Returns true if the file is executable.
     */
    public boolean isExecutable() {

        return this == EXECUTABLE;
    }

}
