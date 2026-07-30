package com.matlasystems.chat.common.util;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Utility methods for working with dates and times.
 * <p>
 * All date/time operations use a single {@link Clock} instance to
 * provide consistent behavior and improve testability.
 *
 * @author MATLA Systems
 * @version 1.0.0
 */
public final class DateUtils {

    /**
     * Default application time zone.
     */
    private static final ZoneId ZONE = ZoneId.systemDefault();

    /**
     * Application clock.
     */
    private static final Clock CLOCK = Clock.system(ZONE);

    /**
     * Formatter for LocalDate.
     */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Formatter for LocalDateTime.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Utility class.
     */
    private DateUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns the current instant.
     *
     * @return current Instant
     */
    public static Instant now() {
        return Instant.now(CLOCK);
    }

    /**
     * Returns today's date.
     *
     * @return current LocalDate
     */
    public static LocalDate today() {
        return LocalDate.now(CLOCK);
    }

    /**
     * Returns the current date and time.
     *
     * @return current LocalDateTime
     */
    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now(CLOCK);
    }

    /**
     * Formats an Instant.
     *
     * @param instant instant to format
     * @return formatted date/time or empty string if null
     */
    public static String format(Instant instant) {

        if (instant == null) {
            return "";
        }

        return DATE_TIME_FORMATTER.format(
                LocalDateTime.ofInstant(
                        instant,
                        ZONE));
    }

    /**
     * Formats a LocalDate.
     *
     * @param date date to format
     * @return formatted date or empty string if null
     */
    public static String format(LocalDate date) {

        if (date == null) {
            return "";
        }

        return DATE_FORMATTER.format(date);
    }

    /**
     * Parses an ISO-8601 instant.
     *
     * @param value ISO-8601 timestamp
     * @return parsed Instant
     */
    public static Instant parseInstant(String value) {
        return Instant.parse(value);
    }

    /**
     * Returns the number of seconds between two instants.
     *
     * @param start start instant
     * @param end end instant
     * @return seconds
     */
    public static long secondsBetween(
            Instant start,
            Instant end) {

        return Duration.between(start, end).getSeconds();
    }

    /**
     * Returns the number of minutes between two instants.
     *
     * @param start start instant
     * @param end end instant
     * @return minutes
     */
    public static long minutesBetween(
            Instant start,
            Instant end) {

        return Duration.between(start, end).toMinutes();
    }

    /**
     * Returns the number of hours between two instants.
     *
     * @param start start instant
     * @param end end instant
     * @return hours
     */
    public static long hoursBetween(
            Instant start,
            Instant end) {

        return Duration.between(start, end).toHours();
    }

    /**
     * Returns the number of days between two instants.
     *
     * @param start start instant
     * @param end end instant
     * @return days
     */
    public static long daysBetween(
            Instant start,
            Instant end) {

        return Duration.between(start, end).toDays();
    }

    /**
     * Determines whether a timestamp has expired.
     *
     * @param timestamp timestamp to evaluate
     * @param timeoutSeconds timeout in seconds
     * @return true if expired
     */
    public static boolean isExpired(
            Instant timestamp,
            long timeoutSeconds) {

        if (timestamp == null) {
            return true;
        }

        return now().isAfter(
                timestamp.plusSeconds(timeoutSeconds));
    }

    /**
     * Adds seconds to an Instant.
     *
     * @param instant source instant
     * @param seconds seconds to add
     * @return updated instant
     */
    public static Instant addSeconds(
            Instant instant,
            long seconds) {

        return instant.plusSeconds(seconds);
    }

    /**
     * Adds minutes to an Instant.
     *
     * @param instant source instant
     * @param minutes minutes to add
     * @return updated instant
     */
    public static Instant addMinutes(
            Instant instant,
            long minutes) {

        return instant.plusSeconds(minutes * 60);
    }

    /**
     * Adds hours to an Instant.
     *
     * @param instant source instant
     * @param hours hours to add
     * @return updated instant
     */
    public static Instant addHours(
            Instant instant,
            long hours) {

        return instant.plusSeconds(hours * 3600);
    }

    /**
     * Returns the current Unix epoch time in milliseconds.
     *
     * @return epoch milliseconds
     */
    public static long currentEpochMillis() {
        return CLOCK.millis();
    }

    /**
     * Returns the current Unix epoch time in seconds.
     *
     * @return epoch seconds
     */
    public static long currentEpochSeconds() {
        return Instant.now(CLOCK).getEpochSecond();
    }

    /**
     * Returns the application's configured time zone.
     *
     * @return application ZoneId
     */
    public static ZoneId zone() {
        return ZONE;
    }

    /**
     * Returns the application's clock.
     *
     * @return application Clock
     */
    public static Clock clock() {
        return CLOCK;
    }

}
