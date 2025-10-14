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
}
