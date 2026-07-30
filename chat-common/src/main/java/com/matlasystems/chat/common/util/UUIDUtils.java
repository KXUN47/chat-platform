package com.matlasystems.chat.common.util;

import java.util.Optional;
import java.util.UUID;

/** Null-safe UUID creation and parsing operations. */
public final class UUIDUtils {
    private UUIDUtils() { throw new UnsupportedOperationException("Utility class"); }
    public static UUID randomUUID() { return UUID.randomUUID(); }
    public static Optional<UUID> parse(String value) {
        if (StringUtils.isBlank(value)) { return Optional.empty(); }
        try { return Optional.of(UUID.fromString(value)); }
        catch (IllegalArgumentException exception) { return Optional.empty(); }
    }
    public static boolean isValid(String value) { return parse(value).isPresent(); }
    public static UUID requireValid(String value) {
        return parse(value).orElseThrow(() -> new IllegalArgumentException("Invalid UUID: " + value));
    }
}
