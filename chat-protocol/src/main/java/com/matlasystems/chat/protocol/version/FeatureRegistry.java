package com.matlasystems.chat.protocol.version;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread-safe registry mapping protocol features to the version
 * in which they were introduced.
 */
public final class FeatureRegistry {

    private final Map<String, ProtocolVersion> features =
            new ConcurrentHashMap<>();

    /**
     * Registers a feature and the version it was introduced in.
     */
    public void register(String feature, ProtocolVersion introducedIn) {

        if (feature == null || feature.isBlank()) {
            throw new IllegalArgumentException("Feature name cannot be blank.");
        }

        if (introducedIn == null) {
            throw new IllegalArgumentException("Introduced version cannot be null.");
        }

        features.put(feature, introducedIn);

    }

    /**
     * Returns the version a feature was introduced in, if registered.
     */
    public Optional<ProtocolVersion> introducedIn(String feature) {

        return Optional.ofNullable(features.get(feature));

    }

    /**
     * Determines whether a feature is available at the given version.
     *
     * A feature is available once {@code version} is greater than or
     * equal to the version it was introduced in.
     */
    public boolean isSupported(String feature, ProtocolVersion version) {

        if (version == null) {
            return false;
        }

        return introducedIn(feature)
                .map(introduced -> version.compareTo(introduced) >= 0)
                .orElse(false);

    }

    /**
     * Removes a feature.
     */
    public void remove(String feature) {

        features.remove(feature);

    }

    /**
     * Removes every registered feature.
     */
    public void clear() {

        features.clear();

    }

    /**
     * Number of registered features.
     */
    public int size() {

        return features.size();

    }

    /**
     * Returns whether the registry is empty.
     */
    public boolean isEmpty() {

        return features.isEmpty();

    }

    /**
     * Immutable view of every registered feature.
     */
    public Map<String, ProtocolVersion> getRegisteredFeatures() {

        return Collections.unmodifiableMap(features);

    }

}
