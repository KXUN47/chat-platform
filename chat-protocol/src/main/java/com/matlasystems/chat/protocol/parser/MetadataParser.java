package com.matlasystems.chat.protocol.parser;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

/** Parses simple scalar metadata fields into an ordered map. */
public final class MetadataParser {

    public ParseResult<Map<String, String>> parse(JsonNode node) {
        if (node == null || node.isNull()) {
            return ParseResult.success(Map.of());
        }
        if (!node.isObject()) {
            return ParseResult.failure("metadata must be a JSON object");
        }

        Map<String, String> metadata = new LinkedHashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            if (!field.getValue().isValueNode()) {
                return ParseResult.failure("metadata field must be scalar: " + field.getKey());
            }
            metadata.put(field.getKey(), field.getValue().asText());
        }
        return ParseResult.success(Map.copyOf(metadata));
    }
}
