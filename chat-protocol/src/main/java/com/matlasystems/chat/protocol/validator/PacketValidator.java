package com.matlasystems.chat.protocol.validator;

import java.util.Objects;

import com.matlasystems.chat.common.protocol.Packet;

/** Validates the complete packet envelope and its payload. */
public final class PacketValidator implements Validator<Packet> {

    private final HeaderValidator headerValidator;
    private final PayloadValidator payloadValidator;

    public PacketValidator() {
        this(new HeaderValidator(), new PayloadValidator());
    }

    public PacketValidator(HeaderValidator headerValidator, PayloadValidator payloadValidator) {
        this.headerValidator = Objects.requireNonNull(headerValidator, "headerValidator must not be null");
        this.payloadValidator = Objects.requireNonNull(payloadValidator, "payloadValidator must not be null");
    }

    @Override
    public ValidationResult validate(Packet packet) {
        if (packet == null) {
            return ValidationResult.invalid("packet", "required", "Packet is required");
        }
        return ValidationResult.valid()
                .merge(headerValidator.validate(packet.getHeader()))
                .merge(payloadValidator.validate(packet.getPayload()));
    }
}
