package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Most of the logic tests can be found at {@code AppointmentDateTimeQueryTest}
 */
public class AppointmentDateTimeQueryTest {
    @Test
    public void equals() {
        AppointmentDateTimeQuery empty = AppointmentDateTimeQuery.empty();
        AppointmentDateTimeQuery first = AppointmentDateTimeQuery.today();
        // comparing same object -> returns true
        assertTrue(first.equals(first));
        assertTrue(empty.equals(empty));

        // compare non-empty date time with empty one -> returns false
        assertFalse(first.equals(empty));

        // comparing same date-time -> returns true
        AppointmentDateTimeQuery second = AppointmentDateTimeQuery.today();
        assertTrue(first.equals(second));

        // different end time -> returns false
        AppointmentDateTimeQuery third = AppointmentDateTimeQuery.withinRelativeDays(2);
        assertFalse(first.equals(third));

        // null -> returns false
        assertFalse(first.equals(null));
    }

    @Test
    public void isValidDateTimeQuery() {
        // null -> false
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery(null));
        // empty string -> false
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery(""));
        // "today" is the only keyword -> true
        assertTrue(AppointmentDateTimeQuery.isValidDateTimeQuery("today"));
        // other keywords -> false
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery("tomorrow"));
        // +days (<= 4 characters) -> true
        assertTrue(AppointmentDateTimeQuery.isValidDateTimeQuery("+234"));
        // +days (> 4 characters) -> false
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery("+23456"));
        // -days (<= 4 characters) -> true
        assertTrue(AppointmentDateTimeQuery.isValidDateTimeQuery("-234"));
        // -days (> 4 characters) -> false
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery("-23456"));
        // single date valid format without HHmm -> true
        assertTrue(AppointmentDateTimeQuery.isValidDateTimeQuery("4-10-2025"));
        // single date invalid format without HHmm -> true
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery("24-103-2025"));
        // single date valid format with HHmm -> true
        assertTrue(AppointmentDateTimeQuery.isValidDateTimeQuery("4-10-2025 1200"));
        // single date valid format with invalid HHmm -> true
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery("4-10-2025 12000"));
        // "to" without date -> false
        assertFalse(AppointmentDateTimeQuery.isValidDateTimeQuery("24-10-2025 1000 to"));
        // "to" with date -> true
        assertTrue(AppointmentDateTimeQuery.isValidDateTimeQuery("24-10-2025 1000 to 25-10-2025 1200"));
    }
}
