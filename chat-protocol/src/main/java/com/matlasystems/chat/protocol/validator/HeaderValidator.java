package com.matlasystems.chat.protocol.validator;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Header;

/** Validates required fields in a protocol packet header. */
public final class HeaderValidator implements Validator<Header> {

    private final CommandValidator commandValidator;
    private final VersionValidator versionValidator;

    public HeaderValidator() {
        this(new CommandValidator(), new VersionValidator());
    }

    public HeaderValidator(CommandValidator commandValidator, VersionValidator versionValidator) {
        this.commandValidator = Objects.requireNonNull(commandValidator, "commandValidator must not be null");
        this.versionValidator = Objects.requireNonNull(versionValidator, "versionValidator must not be null");
    }

    @Override
    public ValidationResult validate(Header header) {
        ValidationResult result = ValidationResult.valid();
        if (header == null) {
            return result.addError("header", "required", "Packet header is required");
        }
        if (header.getPacketId() == null) {
            result.addError("packetId", "required", "Packet ID is required");
        }
        if (header.getTimestamp() == null) {
            result.addError("timestamp", "required", "Packet timestamp is required");
        }
        if (header.getStatus() == null) {
            result.addError("status", "required", "Packet status is required");
        }
        result.merge(commandValidator.validate(header.getCommand()));
        result.merge(versionValidator.validate(header.getProtocolVersion()));
        return result;
    }
}
