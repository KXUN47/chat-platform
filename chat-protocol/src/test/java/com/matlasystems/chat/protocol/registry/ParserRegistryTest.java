package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.parser.CommandParser;

/**
 * Unit tests for {@link ParserRegistry}.
 */
class ParserRegistryTest {

    @Test
    void registersAndFindsAParser() {

        ParserRegistry registry =
                new ParserRegistry();

        CommandParser parser =
                new CommandParser();

        registry.register(
                CommandParser.class,
                parser);

        assertTrue(
                registry.exists(
                        CommandParser.class));

        assertEquals(
                parser,
                registry.find(
                                CommandParser.class)
                        .orElseThrow());

        assertEquals(
                1,
                registry.size());

    }

    @Test
    void rejectsDuplicateRegistration() {

        ParserRegistry registry =
                new ParserRegistry();

        registry.register(
                CommandParser.class,
                new CommandParser());

        CommandParser duplicateParser =
                new CommandParser();

        DuplicateRegistrationException exception =
                assertThrows(
                        DuplicateRegistrationException.class,
                        () -> registry.register(
                                CommandParser.class,
                                duplicateParser));

        assertNotNull(
                exception);

    }

    @Test
    void removeAndClearEmptyTheRegistry() {

        ParserRegistry registry =
                new ParserRegistry();

        registry.register(
                CommandParser.class,
                new CommandParser());

        registry.remove(
                CommandParser.class);

        assertFalse(
                registry.exists(
                        CommandParser.class));

        registry.register(
                CommandParser.class,
                new CommandParser());

        registry.clear();

        assertEquals(
                0,
                registry.size());

    }

    @Test
    void getAllReturnsAnUnmodifiableView() {

        ParserRegistry registry =
                new ParserRegistry();

        registry.register(
                CommandParser.class,
                new CommandParser());

        Map<Class<?>, Object> registeredParsers =
                registry.getAll();

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        registeredParsers::clear);

        assertNotNull(
                exception);

    }

}
