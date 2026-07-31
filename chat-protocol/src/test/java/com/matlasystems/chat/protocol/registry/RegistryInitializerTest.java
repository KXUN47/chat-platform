package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.constants.ProtocolConstants;
import com.matlasystems.chat.protocol.version.ProtocolVersion;

/**
 * Unit tests for {@link RegistryInitializer}.
 */
class RegistryInitializerTest {

    @Test
    void registersTheCurrentVersionAndCoreCommands() {

        ProtocolRegistry protocolRegistry = new ProtocolRegistry();
        RegistryInitializer initializer = new RegistryInitializer(protocolRegistry);

        initializer.initialize();

        assertTrue(initializer.isInitialized());
        assertTrue(protocolRegistry.getVersionManager().isSupported(ProtocolVersion.current()));
        assertEquals(3, protocolRegistry.getCommandRegistry().size());
        assertTrue(protocolRegistry.getCommandRegistry().exists(ProtocolConstants.LOGIN));
        assertTrue(protocolRegistry.getCommandRegistry().exists(ProtocolConstants.LOGOUT));
        assertTrue(protocolRegistry.getCommandRegistry().exists(ProtocolConstants.SEND_MESSAGE));
    }

    @Test
    void secondInitializeCallIsANoOp() {

        ProtocolRegistry protocolRegistry = new ProtocolRegistry();
        RegistryInitializer initializer = new RegistryInitializer(protocolRegistry);

        initializer.initialize();
        initializer.initialize();

        assertEquals(3, protocolRegistry.getCommandRegistry().size());
    }

}
