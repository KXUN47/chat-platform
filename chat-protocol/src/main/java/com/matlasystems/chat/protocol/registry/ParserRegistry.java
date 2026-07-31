package com.matlasystems.chat.protocol.registry;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Stores protocol parsers.
 *
 * Example:
 *
 * PacketParser.class
 * HeaderParser.class
 * PayloadParser.class
 */
public final class ParserRegistry {

    private final Map<Class<?>, Object> parsers =
            new ConcurrentHashMap<>();

    /**
     * Register parser.
     */
    public <T> void register(
            Class<T> type,
            T parser) {

        if (parsers.putIfAbsent(type, parser) != null) {

            throw new DuplicateRegistrationException(
                    "Parser already registered: "
                            + type.getSimpleName());

        }

    }

    /**
     * Lookup parser.
     */
    @SuppressWarnings("unchecked")
    public <T> Optional<T> find(Class<T> type) {

        return Optional.ofNullable(
                (T) parsers.get(type));

    }

    /**
     * Exists.
     */
    public boolean exists(Class<?> type) {
        return parsers.containsKey(type);
    }

    /**
     * Remove parser.
     */
    public void remove(Class<?> type) {
        parsers.remove(type);
    }

    /**
     * Clear registry.
     */
    public void clear() {
        parsers.clear();
    }

    /**
     * Registry size.
     */
    public int size() {
        return parsers.size();
    }

    /**
     * Immutable view.
     */
    public Map<Class<?>, Object> getAll() {

        return Collections.unmodifiableMap(parsers);

    }

}
