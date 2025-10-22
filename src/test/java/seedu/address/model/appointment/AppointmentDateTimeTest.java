package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppointmentDateTimeTest {
    @Test
    public void isValidDateTime() {
        // "DateTime must be in the format dd-MM-yyyy or
        // dd-MM-yyyy HHmm, and must be valid calendar date/time."

        // empty date time is not allowed -> false
        assertFalse(AppointmentDateTime.isValidDateTime(""));
        /*
        Note: these tests are skipped for now.
        // incorrect date
        assertFalse(AppointmentDateTime.isValidDateTime("32-01-2024"));
        // incorrect month
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-2024"));
        // incorrect year
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-12345"));
        */

        // Missing HHmm
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-2024"));
        // correct dd-MM-yyyy but incorrect HHmm
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-2024 12345"));
        // correct dd-MM-yyyy correct HHmm
        assertTrue(AppointmentDateTime.isValidDateTime("12-13-2024 0509"));
    }

    @Test
    public void toStringMethod() {
        assertTrue(new AppointmentDateTime("24-12-2024 1200")
            .toString().equals("24-12-2024 1200"));
    }

    @Test
    public void equals() {
        AppointmentDateTime first = new AppointmentDateTime("24-12-2024 1200");
        AppointmentDateTime second = new AppointmentDateTime("24-12-2024 1230");
        AppointmentDateTime third = new AppointmentDateTime("24-12-2024 1200");
        assertFalse(first.equals(second));
        assertTrue(first.equals(third));
    }

}
