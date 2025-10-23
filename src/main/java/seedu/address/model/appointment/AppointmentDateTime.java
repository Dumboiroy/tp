package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.model.util.DateTimeUtil;

/**
 * Represents the date and time of an appointment.
 * Format: dd-MM-yyyy HHmm (24-hour time).
 */
public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS = "DateTime must be in the format dd-MM-yyyy HHmm, "
            + "and must be valid calendar date/time.";

    private static final DateTimeFormatter FORMAT_DOUBLE_DATE_MONTH = DateTimeFormatter
            .ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter FORMAT_SINGLE_DATE = DateTimeFormatter
            .ofPattern("d-MM-yyyy HHmm");
    private static final DateTimeFormatter FORMAT_SINGLE_MONTH = DateTimeFormatter
            .ofPattern("dd-M-yyyy HHmm");
    private static final DateTimeFormatter FORMAT_SINGLE_DATE_MONTH = DateTimeFormatter
            .ofPattern("d-M-yyyy HHmm");

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
        String[] parts = test.split(" ");
        if (parts.length == 2 && "2400".equals(parts[1])) {
            return false;
        }

        for (DateTimeFormatter formatType : SUPPORTED_FORMATS) {
            if (canParseTime(test, formatType)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLeapYear(LocalDateTime test) {
        int year = test.getYear();
        return year % 4 == 0;
    }

    private static boolean canParseTime(String test, DateTimeFormatter format) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(test, format);

            if (dateTime.getMonth() == Month.FEBRUARY) {
                int date = Integer.parseInt(test.split("-")[0]);

                if (date >= 30) {
                    return false;
                } else if (date == 29) {
                    return isLeapYear(dateTime);
                }
            }

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
