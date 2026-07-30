package com.matlasystems.chat.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * JSON serialization utilities.
 */
public final class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER =
            new ObjectMapper()
                    .findAndRegisterModules()
                    .disable(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private JsonUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Converts an object to JSON.
     */
    public static String toJson(Object object)
            throws JsonProcessingException {

        return OBJECT_MAPPER.writeValueAsString(object);
    }

    /**
     * Converts an object to formatted JSON.
     */
    public static String toPrettyJson(Object object)
            throws JsonProcessingException {

        return OBJECT_MAPPER
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(object);
    }

    /**
     * Converts JSON into an object.
     */
    public static <T> T fromJson(String json,
                                 Class<T> type)
            throws JsonProcessingException {

        return OBJECT_MAPPER.readValue(json, type);
    }

    /**
     * Converts an object to bytes.
     */
    public static byte[] toBytes(Object object)
            throws JsonProcessingException {

        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    /**
     * Converts bytes into an object.
     */
    public static <T> T fromBytes(byte[] bytes,
                                  Class<T> type)
            throws java.io.IOException {

        return OBJECT_MAPPER.readValue(bytes, type);
    }

}
