package com.matlasystems.chat.common.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.matlasystems.chat.common.entity.FileMetadata;

/**
 * Unit tests for {@link FileValidator}.
 */
class FileValidatorTest {

    @Test
    void validatesFileMetadata() {

        FileValidator validator =
                new FileValidator();

        FileMetadata fileMetadata =
                FileMetadata.builder()
                        .senderId(1L)
                        .fileName("file.txt")
                        .contentType("text/plain")
                        .fileSize(1L)
                        .build();

        ValidationResult validResult =
                validator.validate(fileMetadata);

        assertTrue(
                validResult.isValid());

        fileMetadata.setFileName("../bad");

        ValidationResult invalidResult =
                validator.validate(fileMetadata);

        assertTrue(
                invalidResult.isInvalid());
    }

}
