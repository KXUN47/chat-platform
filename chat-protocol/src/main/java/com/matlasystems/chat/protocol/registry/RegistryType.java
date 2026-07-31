package com.matlasystems.chat.protocol.registry;

public enum RegistryType {

    /**
     * Protocol commands.
     */
    COMMAND,

    /**
     * Packet handlers.
     */
    HANDLER,

    /**
     * Packet parsers.
     */
    PARSER,

    /**
     * Packet serializers.
     */
    SERIALIZER,

    /**
     * Packet validators.
     */
    VALIDATOR,

    /**
     * Supported protocol versions.
     */
    VERSION

}
