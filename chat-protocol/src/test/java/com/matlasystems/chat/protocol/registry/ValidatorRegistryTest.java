package com.matlasystems.chat.protocol.registry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.protocol.validator.CommandValidator;

/**
 * Unit tests for {@link ValidatorRegistry}.
 */
class ValidatorRegistryTest {

    @Test
    void registersAndFindsAValidator() {

        ValidatorRegistry registry =
                new ValidatorRegistry();

        CommandValidator validator =
                new CommandValidator();

        registry.register(
                CommandValidator.class,
                validator);

        assertTrue(
                registry.exists(
                        CommandValidator.class));

        assertEquals(
                validator,
                registry.find(
                                CommandValidator.class)
                        .orElseThrow());

        assertEquals(
                1,
                registry.size());

    }

    @Test
    void rejectsNullTypeOrValidator() {

        ValidatorRegistry registry =
                new ValidatorRegistry();

        CommandValidator validator =
                new CommandValidator();

        IllegalArgumentException nullTypeException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(
                                null,
                                validator));

        assertNotNull(
                nullTypeException);

        IllegalArgumentException nullValidatorException =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> registry.register(
                                CommandValidator.class,
                                null));

        assertNotNull(
                nullValidatorException);

    }

    @Test
    void rejectsDuplicateRegistration() {

        ValidatorRegistry registry =
                new ValidatorRegistry();

        registry.register(
                CommandValidator.class,
                new CommandValidator());

        CommandValidator duplicateValidator =
                new CommandValidator();

        DuplicateRegistrationException exception =
                assertThrows(
                        DuplicateRegistrationException.class,
                        () -> registry.register(
                                CommandValidator.class,
                                duplicateValidator));

        assertNotNull(
                exception);

    }

    @Test
    void removeAndClearEmptyTheRegistry() {

        ValidatorRegistry registry =
                new ValidatorRegistry();

        registry.register(
                CommandValidator.class,
                new CommandValidator());

        registry.remove(
                CommandValidator.class);

        assertEquals(
                0,
                registry.size());

        registry.register(
                CommandValidator.class,
                new CommandValidator());

        registry.clear();

        assertEquals(
                0,
                registry.size());

    }

}
