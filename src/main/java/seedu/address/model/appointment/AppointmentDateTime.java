package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.model.util.DateTimeUtil;

/**
 * Represents the date and time of an appointment.
 * Format: dd-MM-yyyy HHmm (24-hour time).
 */
public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime must be in the format dd-MM-yyyy HHmm, "
            + "and must be valid calendar date/time.";

    private static final DateTimeFormatter FORMAT_DOUBLE_DATE_MONTH = DateTimeFormatter
            .ofPattern("dd-MM-yyyy HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter FORMAT_SINGLE_DATE = DateTimeFormatter
            .ofPattern("d-MM-yyyy HHmm")
            .withResolverStyle(ResolverStyle.STRICT);;
    private static final DateTimeFormatter FORMAT_SINGLE_MONTH = DateTimeFormatter
            .ofPattern("dd-M-yyyy HHmm")
            .withResolverStyle(ResolverStyle.STRICT);;
    private static final DateTimeFormatter FORMAT_SINGLE_DATE_MONTH = DateTimeFormatter
            .ofPattern("d-M-yyyy HHmm")
            .withResolverStyle(ResolverStyle.STRICT);
    ;
    private static final DateTimeFormatter[] SUPPORTED_FORMATS = {
        FORMAT_DOUBLE_DATE_MONTH,
        FORMAT_SINGLE_DATE,
        FORMAT_SINGLE_MONTH,
        FORMAT_SINGLE_DATE_MONTH,
    };

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
     * Returns true if the given string passes the parse.
     */
    public static boolean isValidDateTime(String test) {
        //reject time 2400, not handled by parse
        String[] parts = test.split(" ");
        if (parts.length == 2) {
            return false;
        }

        for (DateTimeFormatter formatType : SUPPORTED_FORMATS) {
            if (parseTime(test, formatType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given string matches the date-time format and is a valid time.
     */
    public static boolean parseTime(String test, DateTimeFormatter format) {
        try {
            LocalDateTime.parse(test, format);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
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
