package seedu.address.model.util;

import java.time.Duration;

/**
 * Utility class for converting between {@link Duration} objects and their string representations.
 * <p>
 * The string representation is expected to be the number of minutes as a string (e.g., "90" for 90 minutes).
 * </p>
 */
public class DurationUtil {
    /**
     * Converts a string representing the number of minutes to a {@link Duration} object.
     * <p>
     * If the input is {@code null} or empty, returns {@link Duration#ZERO}.
     * </p>
     *
     * @param durationStr the string representation of the duration in minutes
     * @return the corresponding {@link Duration} object
     * @throws NumberFormatException if the input string is not a valid integer
     */
    public static Duration durationFromString(String durationStr) {
        if (durationStr == null || durationStr.isEmpty()) {
            return Duration.ZERO;
        }
        int minutes = Integer.parseInt(durationStr);
        return Duration.ofMinutes(minutes);
    }

    /**
     * Converts a {@link Duration} object to its string representation in minutes.
     * <p>
     * If the duration is {@code null} or zero, returns "0".
     * </p>
     *
     * @param duration the {@link Duration} object to convert
     * @return the string representation of the duration in minutes
     */
    public static String stringFromDuration(Duration duration) {
        if (duration.isZero()) {
            return "0";
        }
        if (duration == null) {
            return "<not set>";
        }
        long minutes = duration.toMinutes();
        return Long.toString(minutes);
    }
}
