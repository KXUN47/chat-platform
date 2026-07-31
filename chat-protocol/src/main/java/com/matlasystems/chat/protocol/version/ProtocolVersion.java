package com.matlasystems.chat.protocol.version;

import java.util.Objects;

/**
 * Immutable representation of a protocol version, expressed as
 * major.minor.patch components.
 */
public final class ProtocolVersion implements Comparable<ProtocolVersion> {

    /**
     * The initial released version of the protocol.
     */
    public static final ProtocolVersion V1_0_0 = new ProtocolVersion(1, 0, 0);

    private final int major;

    private final int minor;

    private final int patch;

    private ProtocolVersion(int major, int minor, int patch) {

        if (major < 0 || minor < 0 || patch < 0) {
            throw new IllegalArgumentException("Version components cannot be negative.");
        }

        this.major = major;
        this.minor = minor;
        this.patch = patch;

    }

    /**
     * Creates a protocol version from major, minor, and patch components.
     */
    public static ProtocolVersion of(int major, int minor, int patch) {

        return new ProtocolVersion(major, minor, patch);

    }

    /**
     * Creates a protocol version with no patch component.
     */
    public static ProtocolVersion of(int major, int minor) {

        return new ProtocolVersion(major, minor, 0);

    }

    /**
     * Returns the default protocol version used by this application.
     */
    public static ProtocolVersion current() {

        return V1_0_0;

    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    /**
     * Determines whether this version shares the same major component
     * as another version.
     */
    public boolean isSameMajor(ProtocolVersion other) {

        return other != null && this.major == other.major;

    }

    @Override
    public int compareTo(ProtocolVersion other) {

        int result = Integer.compare(this.major, other.major);

        if (result == 0) {
            result = Integer.compare(this.minor, other.minor);
        }

        if (result == 0) {
            result = Integer.compare(this.patch, other.patch);
        }

        return result;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProtocolVersion other)) {
            return false;
        }

        return major == other.major
                && minor == other.minor
                && patch == other.patch;

    }

    @Override
    public int hashCode() {

        return Objects.hash(major, minor, patch);

    }

    @Override
    public String toString() {

        return major + "." + minor + "." + patch;

    }

}
