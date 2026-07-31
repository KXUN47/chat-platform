package com.matlasystems.chat.protocol.validator;

/** Provides reusable standard protocol validator instances. */
public final class ValidatorFactory {

    private static final CommandValidator COMMAND_VALIDATOR = new CommandValidator();
    private static final VersionValidator VERSION_VALIDATOR = new VersionValidator();
    private static final HeaderValidator HEADER_VALIDATOR = new HeaderValidator(
            COMMAND_VALIDATOR, VERSION_VALIDATOR);
    private static final PayloadValidator PAYLOAD_VALIDATOR = new PayloadValidator();
    private static final PacketValidator PACKET_VALIDATOR = new PacketValidator(
            HEADER_VALIDATOR, PAYLOAD_VALIDATOR);

    private ValidatorFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static CommandValidator commandValidator() {
        return COMMAND_VALIDATOR;
    }

    public static VersionValidator versionValidator() {
        return VERSION_VALIDATOR;
    }

    public static HeaderValidator headerValidator() {
        return HEADER_VALIDATOR;
    }

    public static PayloadValidator payloadValidator() {
        return PAYLOAD_VALIDATOR;
    }

    public static PacketValidator packetValidator() {
        return PACKET_VALIDATOR;
    }
}
