package com.matlasystems.chat.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/**
 * Utility methods for common file operations.
 *
 * <p>Temporary files are created inside the application's own temporary
 * directory instead of the JVM default temporary directory.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public final class FileUtils {

    /**
     * Common null-check message
     */
    private static final String PATH_NULL_MESSAGE = "path must not be null";

    /**
     * Application data directory.
     */
    private static final Path DATA_DIRECTORY =
            Path.of("data");

    /**
     * Application temporary directory.
     */
    private static final Path TEMP_DIRECTORY =
            DATA_DIRECTORY.resolve("temp");

    private FileUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns true if the file exists.
     *
     * @param path file path
     * @return true if the file exists
     */
    public static boolean exists(Path path) {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        return Files.exists(path);
    }

    /**
     * Creates directories if they do not already exist.
     *
     * @param path directory path
     * @return created directory
     * @throws IOException if creation fails
     */
    public static Path createDirectories(Path path)
            throws IOException {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        return Files.createDirectories(path);
    }

    /**
     * Reads a file into memory.
     *
     * @param path file path
     * @return file bytes
     * @throws IOException if reading fails
     */
    public static byte[] readBytes(Path path)
            throws IOException {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        return Files.readAllBytes(path);
    }

    /**
     * Writes bytes to a file.
     *
     * @param path destination
     * @param bytes file contents
     * @return written file
     * @throws IOException if writing fails
     */
    public static Path writeBytes(
            Path path,
            byte[] bytes)
            throws IOException {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);
        Objects.requireNonNull(bytes, "bytes must not be null");

        Path parent = path.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        return Files.write(path, bytes);
    }

    /**
     * Deletes a file if it exists.
     *
     * @param path file path
     * @return true if deleted
     * @throws IOException if deletion fails
     */
    public static boolean delete(Path path)
            throws IOException {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        return Files.deleteIfExists(path);
    }

    /**
     * Copies a file.
     *
     * @param source source file
     * @param destination destination file
     * @return copied file
     * @throws IOException if copy fails
     */
    public static Path copy(
            Path source,
            Path destination)
            throws IOException {

        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(destination, "destination must not be null");

        Path parent = destination.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        return Files.copy(
                source,
                destination,
                StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Moves a file.
     *
     * @param source source file
     * @param destination destination file
     * @return moved file
     * @throws IOException if move fails
     */
    public static Path move(
            Path source,
            Path destination)
            throws IOException {

        Objects.requireNonNull(source, "source must not be null");
        Objects.requireNonNull(destination, "destination must not be null");

        Path parent = destination.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        return Files.move(
                source,
                destination,
                StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Returns the file size.
     *
     * @param path file path
     * @return file size in bytes
     * @throws IOException if lookup fails
     */
    public static long size(Path path)
            throws IOException {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        return Files.size(path);
    }

    /**
     * Returns the filename.
     *
     * @param path file path
     * @return filename
     */
    public static String filename(Path path) {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        return path.getFileName().toString();
    }

    /**
     * Returns the file extension.
     *
     * @param path file path
     * @return extension without the dot
     */
    public static String extension(Path path) {

        Objects.requireNonNull(path, PATH_NULL_MESSAGE);

        String filename = filename(path);

        int index = filename.lastIndexOf('.');

        return index < 0
                ? ""
                : filename.substring(index + 1);
    }

    /**
     * Creates a temporary file inside the application's temporary directory.
     *
     * @param prefix filename prefix
     * @param suffix filename suffix
     * @return created temporary file
     * @throws IOException if creation fails
     */
    public static Path createTempFile(
            String prefix,
            String suffix)
            throws IOException {

        Files.createDirectories(TEMP_DIRECTORY);

        return Files.createTempFile(
                TEMP_DIRECTORY,
                prefix,
                suffix);
    }

    /**
     * Returns the application's temporary directory.
     *
     * @return temporary directory
     */
    public static Path tempDirectory() {
        return TEMP_DIRECTORY;
    }
}
