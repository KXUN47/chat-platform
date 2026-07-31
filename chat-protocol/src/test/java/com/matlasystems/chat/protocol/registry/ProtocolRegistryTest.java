package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link ProtocolRegistry}.
 */
class ProtocolRegistryTest {

    @Test
    void wiresUpEverySubRegistry() {

        ProtocolRegistry registry = new ProtocolRegistry();

        assertNotNull(registry.getCommandRegistry());
        assertNotNull(registry.getHandlerRegistry());
        assertNotNull(registry.getParserRegistry());
        assertNotNull(registry.getSerializerRegistry());
        assertNotNull(registry.getValidatorRegistry());
        assertNotNull(registry.getVersionManager());
    }

    @Test
    void returnsTheSameInstanceOnRepeatedCalls() {

        ProtocolRegistry registry = new ProtocolRegistry();

        assertSame(registry.getCommandRegistry(), registry.getCommandRegistry());
        assertSame(registry.getVersionManager(), registry.getVersionManager());
    }

}
