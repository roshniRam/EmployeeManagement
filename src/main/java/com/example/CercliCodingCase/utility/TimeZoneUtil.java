package com.example.CercliCodingCase.utility;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for all timezone related functionality
 */
public class TimeZoneUtil {

    /**
     * We will store all time in UTC
     */
    private static final ZoneId SERVER_ZONE_ID = ZoneId.of("UTC");

    /**
     * Get the current date-time in the server's timezone (UTC).
     */
    public static LocalDateTime getServerDateTime() {
        return ZonedDateTime.now(SERVER_ZONE_ID).toLocalDateTime();
    }

    /**
     * Convert UTC datetime to the user's local timezone.
     */
    public static LocalDateTime convertUtcToLocal(final LocalDateTime utcDateTime, final ZoneId localZoneId) {
        final ZonedDateTime utcZonedDateTime = utcDateTime.atZone(SERVER_ZONE_ID);
        return utcZonedDateTime.withZoneSameInstant(localZoneId).toLocalDateTime();
    }

    /**
     * Format the LocalDateTime based on the user's region.
     */
    public String formatDateTime(final LocalDateTime dateTime, final String timezone) {
        DateTimeFormatter formatter;
        if ("Asia/Kolkata".equals(timezone)) {
            formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        } else if ("Asia/Dubai".equals(timezone)) {
            formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        } else if ("America/New_York".equals(timezone)) {
            formatter = DateTimeFormatter.ofPattern("HH:mm:ss MM-dd-yyyy");
        } else {
            // Default format
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        return dateTime.format(formatter);
    }
}
