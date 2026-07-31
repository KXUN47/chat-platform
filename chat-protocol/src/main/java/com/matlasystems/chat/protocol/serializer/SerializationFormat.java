package com.matlasystems.chat.protocol.serializer;

/** Formats supported by the protocol serializer subsystem. */
public enum SerializationFormat {

    JSON("application/json");

    private final String contentType;

    SerializationFormat(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public static SerializationFormat fromContentType(String contentType) {
        for (SerializationFormat format : values()) {
            if (format.contentType.equalsIgnoreCase(contentType)) {
                return format;
            }
        }
        throw new UnsupportedSerializationException("Unsupported content type: " + contentType);
    }
}
