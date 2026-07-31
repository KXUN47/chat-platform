package com.matlasystems.chat.protocol.version;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Central point of coordination for supported protocol versions, their
 * metadata, feature availability, and negotiation.
 *
 * Thread-safe.
 */
public final class VersionManager {

    private final Map<ProtocolVersion, VersionMetadata> versions =
            new ConcurrentHashMap<>();

    private final FeatureRegistry featureRegistry;

    private final VersionNegotiator negotiator;

    private final AtomicReference<ProtocolVersion> currentVersion;

    public VersionManager() {

        this(new FeatureRegistry(), new VersionNegotiator());

    }

    public VersionManager(FeatureRegistry featureRegistry, VersionNegotiator negotiator) {

        this.featureRegistry = featureRegistry;
        this.negotiator = negotiator;
        this.currentVersion = new AtomicReference<>(ProtocolVersion.current());

    }

    /**
     * Registers a supported version and its metadata.
     */
    public void register(VersionMetadata metadata) {

        if (metadata == null) {
            throw new IllegalArgumentException("Version metadata cannot be null.");
        }

        versions.put(metadata.getVersion(), metadata);

    }

    /**
     * Determines whether a version is currently supported.
     */
    public boolean isSupported(ProtocolVersion version) {

        return versions.containsKey(version);

    }

    /**
     * Returns metadata for a version, if registered.
     */
    public Optional<VersionMetadata> metadataFor(ProtocolVersion version) {

        return Optional.ofNullable(versions.get(version));

    }

    /**
     * Returns every registered version.
     */
    public Set<ProtocolVersion> getSupportedVersions() {

        return Collections.unmodifiableSet(versions.keySet());

    }

    /**
     * Returns the highest registered version.
     *
     * @throws UnsupportedProtocolVersionException if none are registered
     */
    public ProtocolVersion latestVersion() {

        return versions.keySet().stream()
                .max(VersionComparator.ASCENDING)
                .orElseThrow(() ->
                        new UnsupportedProtocolVersionException(ProtocolVersion.current()));

    }

    /**
     * Negotiates the version to use for a requesting client, validating
     * that the result is registered with this manager.
     *
     * @throws UnsupportedProtocolVersionException if unsupported
     */
    public ProtocolVersion negotiate(ProtocolVersion requested) {

        ProtocolVersion negotiated = negotiator.negotiate(requested, getSupportedVersions());

        if (!isSupported(negotiated)) {
            throw new UnsupportedProtocolVersionException(negotiated);
        }

        return negotiated;

    }

    /**
     * Determines whether a feature is available at a given version.
     */
    public boolean supportsFeature(String feature, ProtocolVersion version) {

        return featureRegistry.isSupported(feature, version);

    }

    /**
     * Returns the feature registry backing this manager.
     */
    public FeatureRegistry getFeatureRegistry() {

        return featureRegistry;

    }

    /**
     * Returns the version currently active on this node.
     */
    public ProtocolVersion getCurrentVersion() {

        return currentVersion.get();

    }

    /**
     * Sets the version currently active on this node.
     *
     * @throws UnsupportedProtocolVersionException if the version is not registered
     */
    public void setCurrentVersion(ProtocolVersion version) {

        if (!isSupported(version)) {
            throw new UnsupportedProtocolVersionException(version);
        }

        this.currentVersion.set(version);

    }

    /**
     * Removes every registered version and feature.
     */
    public void clear() {

        versions.clear();
        featureRegistry.clear();

    }

}
