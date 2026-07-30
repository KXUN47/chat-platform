package com.matlasystems.chat.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

class JsonUtilsTest {

    @Test
    void serializesAndDeserializesJson() throws Exception {

        String json =
                JsonUtils.toJson(
                        Map.of("key", "value"));

        assertEquals(
                "value",
                JsonUtils.fromJson(json, Map.class)
                        .get("key"));

        assertTrue(
                JsonUtils.toPrettyJson(
                        Map.of("key", 1))
                        .contains("key"));
    }

}
