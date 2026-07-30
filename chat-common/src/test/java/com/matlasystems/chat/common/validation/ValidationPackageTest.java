package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.entity.FileMetadata;
import com.matlasystems.chat.common.entity.Session;
import com.matlasystems.chat.common.enums.CommandType;
import com.matlasystems.chat.common.enums.SessionStatus;
import com.matlasystems.chat.common.protocol.PacketFactory;

/**
 * Integration tests covering the validation package.
 */
class ValidationPackageTest {

    @Test
    void stringValidatorsAcceptValidValuesAndRejectInvalidValues() {

        UsernameValidator usernameValidator =
                new UsernameValidator();

        PasswordValidator passwordValidator =
                new PasswordValidator();

        EmailValidator emailValidator =
                new EmailValidator();

        MessageValidator messageValidator =
                new MessageValidator();

        assertTrue(
                usernameValidator.validate("matla.user")
                        .isValid());

        assertTrue(
                passwordValidator.validate("StrongPass1")
                        .isValid());

        assertTrue(
                emailValidator.validate("user@example.com")
                        .isValid());

        assertTrue(
                messageValidator.validate("Hello")
                        .isValid());

        ValidationResult invalidResult =
                usernameValidator.validate("a ");

        assertTrue(
                invalidResult.isInvalid());

        assertEquals(
                "username",
                invalidResult.getErrors()
                        .getFirst()
                        .getField());
    }

    @Test
    void compositeValidatorCombinesAllFailures() {

        CompositeValidator<String> validator =
                new CompositeValidator<>();

        validator.add(
                value -> ValidationResult.invalid(
                        "first",
                        "invalid",
                        "first failure"));

        validator.add(
                value -> ValidationResult.invalid(
                        "second",
                        "invalid",
                        "second failure"));

        ValidationResult result =
                validator.validate("value");

        assertEquals(
                2,
                result.getErrors().size());

        assertTrue(
                result.isInvalid());
    }

    @Test
    void packetValidatorRequiresStandardHeaderFields() {

        PacketValidator validator =
                new PacketValidator();

        ValidationResult validResult =
                validator.validate(
                        PacketFactory.request(CommandType.PING));

        ValidationResult invalidResult =
                validator.validate(null);

        assertTrue(
                validResult.isValid());

        assertTrue(
                invalidResult.isInvalid());
    }

    @Test
    void fileAndSessionValidatorsCheckDomainRequirements() {

        FileValidator fileValidator =
                new FileValidator();

        FileMetadata fileMetadata =
                FileMetadata.builder()
                        .senderId(1L)
                        .fileName("report.pdf")
                        .contentType("application/pdf")
                        .fileSize(1024)
                        .build();

        ValidationResult validFile =
                fileValidator.validate(fileMetadata);

        assertTrue(
                validFile.isValid());

        fileMetadata.setFileName("../unsafe");

        ValidationResult invalidFile =
                fileValidator.validate(fileMetadata);

        assertTrue(
                invalidFile.isInvalid());

        SessionValidator sessionValidator =
                new SessionValidator();

        Session session =
                new Session(
                        UUID.randomUUID(),
                        1L,
                        Instant.now(),
                        Instant.now(),
                        "127.0.0.1",
                        8080,
                        SessionStatus.ACTIVE);

        ValidationResult validSession =
                sessionValidator.validate(session);

        assertTrue(
                validSession.isValid());

        session.setPort(0);

        ValidationResult invalidSession =
                sessionValidator.validate(session);

        assertTrue(
                invalidSession.isInvalid());
    }

    @Test
    void validationFacadeDelegatesToStandardValidators() {

        ValidationResult emailResult =
                ValidationUtils.validateEmail(
                        "valid@example.com");

        ValidationResult messageResult =
                ValidationUtils.validateMessage(" ");

        assertTrue(
                emailResult.isValid());

        assertTrue(
                messageResult.isInvalid());
    }

    @Test
    void resultErrorAndValidationConstantsExposeStableValues() {

        ValidationError error =
                new ValidationError(
                        ValidationFields.USERNAME,
                        ValidationCodes.REQUIRED,
                        ValidationMessages.USERNAME_REQUIRED);

        ValidationResult result =
                ValidationResult.valid()
                        .addError(error);

        assertTrue(
                result.isInvalid());

        assertEquals(
                error,
                result.getErrors()
                        .getFirst());

        List<ValidationError> errors =
                result.getErrors();

        UnsupportedOperationException exception =
                assertThrows(
                        UnsupportedOperationException.class,
                        () -> errors.add(error));

        assertNotNull(exception);

        assertEquals(
                "username",
                ValidationFields.USERNAME);

        assertEquals(
                "required",
                ValidationCodes.REQUIRED);

        assertEquals(
                "Username is required",
                ValidationMessages.USERNAME_REQUIRED);

        Validator<String> validator =
                value -> ValidationResult.valid();

        ValidationResult validationResult =
                validator.validate("value");

        assertTrue(
                validationResult.isValid());
    }

}
