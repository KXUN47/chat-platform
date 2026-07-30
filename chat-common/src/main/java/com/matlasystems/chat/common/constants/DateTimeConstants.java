package com.matlasystems.chat.common.constants;

/**
 * Date, time and duration constants.
 */
public final class DateTimeConstants {

    private DateTimeConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static final String UTC_ZONE_ID = "UTC";
    public static final String ISO_INSTANT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    public static final String ISO_OFFSET_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final long MILLIS_PER_SECOND = 1_000L;
    public static final long SECONDS_PER_MINUTE = 60L;
    public static final long MINUTES_PER_HOUR = 60L;
    public static final long HOURS_PER_DAY = 24L;
    public static final long MILLIS_PER_MINUTE = MILLIS_PER_SECOND * SECONDS_PER_MINUTE;
    public static final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * MINUTES_PER_HOUR;
    public static final long MILLIS_PER_DAY = MILLIS_PER_HOUR * HOURS_PER_DAY;
}
