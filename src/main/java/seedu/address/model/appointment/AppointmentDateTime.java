package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.model.util.DateTimeUtil;

/**
 * Represents the date and time of an appointment.
 * Format: dd-MM-yyyy HHmm (24-hour time).
 */
public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "DateTime must be in the format dd-MM-yyyy HHmm, and must be valid calendar date/time.";
    // Very simple regex: date part mandatory, optional time part
    public static final String VALIDATION_REGEX =
            "^\\d{1,2}-\\d{1,2}-\\d{4} \\d{4}$";

    public final LocalDateTime dateTime;

    /**
     * Constructs an {@code AppointmentDateTime} with the specified date-time string.
     * The input must match the expected format.
     */
    public AppointmentDateTime(String dateTimeString) {
        requireNonNull(dateTimeString);
        checkArgument(isValidDateTime(dateTimeString), MESSAGE_CONSTRAINTS);
        this.dateTime = DateTimeUtil.localDateTimeFromString(dateTimeString);
    }

    /**
     * Returns true if the given string matches the date-time format.
     */
    public static boolean isValidDateTime(String test) {
        return test.matches(VALIDATION_REGEX);
        // NOTE: Later you can plug in LocalDate/LocalDateTime parsing for stricter checks
    }

    @Override
    public String toString() {
        return DateTimeUtil.stringFromLocalDateTime(dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentDateTime
                && dateTime.equals(((AppointmentDateTime) other).dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}
