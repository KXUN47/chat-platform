package com.matlasystems.chat.protocol.version;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.matlasystems.chat.protocol.parser.ParseResult;

/**
 * Parses version strings such as "1.2.3" or "1.2" into
 * {@link ProtocolVersion} instances.
 */
public final class VersionParser {

    private static final Pattern VERSION_PATTERN =
            Pattern.compile("^(\\d+)\\.(\\d+)(?:\\.(\\d+))?$");

    /**
     * Parses a version from a JSON node.
     */
    public ParseResult<ProtocolVersion> parse(JsonNode node) {

        if (node == null || !node.isTextual()) {
            return ParseResult.failure("version must be a string");
        }

        return parse(node.asText());

    }

    /**
     * Parses a version from a string such as "1.2.3" or "1.2".
     */
    public ParseResult<ProtocolVersion> parse(String value) {

        if (value == null || value.isBlank()) {
            return ParseResult.failure("version is required");
        }

        Matcher matcher = VERSION_PATTERN.matcher(value.trim());

        if (!matcher.matches()) {
            return ParseResult.failure("Malformed version string: " + value);
        }

        try {

            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int patch = matcher.group(3) == null ? 0 : Integer.parseInt(matcher.group(3));

            return ParseResult.success(ProtocolVersion.of(major, minor, patch));

        } catch (NumberFormatException exception) {

            return ParseResult.failure("Malformed version string: " + value, exception);

        }

    }

}
