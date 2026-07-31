package com.matlasystems.chat.protocol.validator;

import com.matlasystems.chat.common.protocol.ProtocolVersion;

/** Validates that a packet uses a supported protocol version. */
public final class VersionValidator implements Validator<ProtocolVersion> {

    @Override
    public ValidationResult validate(ProtocolVersion version) {
        return version == null
                ? ValidationResult.invalid("protocolVersion", "required", "Protocol version is required")
                : ValidationResult.valid();
    }
}
