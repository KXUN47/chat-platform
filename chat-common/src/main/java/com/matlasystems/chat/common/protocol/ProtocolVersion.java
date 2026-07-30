package com.matlasystems.chat.common.protocol;

import java.util.Arrays;
import java.util.Optional;

/** Versions of the MATLA chat wire protocol understood by this application. */
public enum ProtocolVersion {

    V1_0("1.0");

    private final String value;

    ProtocolVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProtocolVersion current() {
        return V1_0;
    }

    public static Optional<ProtocolVersion> fromValue(String value) {
        return Arrays.stream(values())
                .filter(version -> version.value.equals(value))
                .findFirst();
    }

    @Override
    public String toString() {
        return value;
    }
}
