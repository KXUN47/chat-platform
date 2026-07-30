package com.matlasystems.chat.common.util;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * Integration tests covering the common utility classes.
 */
class UtilityPackageTest {

    @TempDir
    Path temporaryDirectory;

    @Test
    void stringUtilitiesHandleBlankAndTextValues() {

        assertTrue(StringUtils.isBlank(" \t"));
        assertTrue(StringUtils.hasText(" chat "));
        assertNull(StringUtils.trimToNull("  "));
        assertEquals(
                "chat platform",
                StringUtils.normalizeWhitespace("  chat\n platform  "));
        assertEquals(
                "hello",
                StringUtils.decapitalize("Hello"));
        assertEquals(
                "abc",
                StringUtils.truncate("abcdef", 3));

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> StringUtils.truncate("a", -1));

        assertNotNull(exception);
    }

    @Test
    void uuidUtilitiesParseAndValidateValues() {

        UUID value = UUIDUtils.randomUUID();

        assertTrue(UUIDUtils.isValid(value.toString()));

        assertEquals(
                value,
                UUIDUtils.parse(value.toString())
                        .orElseThrow());

        assertTrue(
                UUIDUtils.parse("not-a-uuid")
                        .isEmpty());

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> UUIDUtils.requireValid("invalid"));

        assertNotNull(exception);
    }

    @Test
    void primitiveValidationUtilitiesApplySharedRules() {

        assertTrue(
                ValidationUtils.isValidUsername("matla.user-1"));

        assertFalse(
                ValidationUtils.isValidUsername("no spaces"));

        assertTrue(
                ValidationUtils.isValidPassword("StrongPass1"));

        assertTrue(
                ValidationUtils.isValidEmail("user@example.com"));

        assertTrue(
                ValidationUtils.isValidFileName("report.pdf"));

        assertFalse(
                ValidationUtils.isValidFileName("../report.pdf"));

        assertTrue(
                ValidationUtils.isValidPort(8080));

        assertTrue(
                ValidationUtils.isValidIpv4Address("127.0.0.1"));
    }

    @Test
    void existingGeneralUtilitiesProvideSafeResults() {

        assertTrue(
                CollectionUtils.isEmpty(null));

        assertEquals(
                "first",
                CollectionUtils.first(
                        List.of("first", "last")));

        assertEquals(
                "last",
                CollectionUtils.last(
                        List.of("first", "last")));

        assertEquals(
                60,
                DateUtils.secondsBetween(
                        Instant.EPOCH,
                        Instant.EPOCH.plusSeconds(60)));

        IllegalStateException root =
                new IllegalStateException("root");

        assertSame(
                root,
                ExceptionUtils.rootCause(
                        new RuntimeException(
                                "wrapper",
                                root)));

        assertTrue(
                ExceptionUtils.summary(root)
                        .contains("root"));

        assertTrue(
                NetworkUtils.isValidPort(65_535));

        assertFalse(
                NetworkUtils.isValidPort(0));

        assertEquals(
                12,
                RandomUtils.alphanumeric(12).length());

        assertTrue(
                RandomUtils.uuid().version() >= 1);
    }

    @Test
    void fileUtilitiesReadWriteCopyMoveAndDeleteFiles()
            throws Exception {

        Path source =
                temporaryDirectory.resolve("source.txt");

        Path copy =
                temporaryDirectory.resolve("nested/copy.txt");

        Path moved =
                temporaryDirectory.resolve("moved.txt");

        FileUtils.writeBytes(
                source,
                "chat".getBytes());

        assertTrue(
                FileUtils.exists(source));

        assertEquals(
                4,
                FileUtils.size(source));

        assertEquals(
                "source.txt",
                FileUtils.filename(source));

        assertEquals(
                "txt",
                FileUtils.extension(source));

        FileUtils.copy(source, copy);

        FileUtils.move(copy, moved);

        assertArrayEquals(
                "chat".getBytes(),
                FileUtils.readBytes(moved));

        assertTrue(
                FileUtils.delete(moved));
    }

    @Test
    void jsonAndPasswordUtilitiesSerializeAndProtectValues()
            throws Exception {

        String json =
                JsonUtils.toJson(
                        Map.of("message", "hello"));

        assertTrue(
                json.contains("hello"));

        assertEquals(
                "hello",
                JsonUtils.fromJson(json, Map.class)
                        .get("message"));

        assertArrayEquals(
                JsonUtils.toBytes(
                        Map.of("value", 1)),
                JsonUtils.toBytes(
                        Map.of("value", 1)));

        String password = "StrongPass1!";

        String hash =
                PasswordUtils.hash(password);

        assertTrue(
                PasswordUtils.verify(
                        password,
                        hash));

        assertTrue(
                PasswordUtils.isStrong(password));

        assertTrue(
                PasswordUtils.secureEquals(
                        password,
                        password));

        assertFalse(
                PasswordUtils.secureEquals(
                        password,
                        "other"));

        assertEquals(
                12,
                PasswordUtils.generateTemporaryPassword()
                        .length());

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> PasswordUtils.validate("weak"));

        assertNotNull(exception);
    }

}
