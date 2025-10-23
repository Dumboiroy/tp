package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppointmentDateTimeTest {
    @Test
    public void isValidDateTime() {
        // "DateTime must be in the format dd-MM-yyyy HHmm, and must be valid calendar date/time."

        // empty date time is not allowed -> false
        assertFalse(AppointmentDateTime.isValidDateTime(""));

        // incorrect date
        assertFalse(AppointmentDateTime.isValidDateTime("32-01-2024 1900"));
        // incorrect month
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-2024 1800"));
        // incorrect year
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-12345 1700"));

        // correct dd-MM-yyyy and incorrect HHmm
        assertFalse(AppointmentDateTime.isValidDateTime("12-12-2024 12345"));
        // invalid dd-MM-yyyy correct HHmm
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-2024 0509"));

        //edge case HH
        assertFalse(AppointmentDateTime.isValidDateTime("12-12-2025 2400"));
        //edge case mm
        assertFalse(AppointmentDateTime.isValidDateTime("12-9-2025 1260"));
        //edge case HHmm
        assertFalse(AppointmentDateTime.isValidDateTime("12-12-2025 2460"));
        assertFalse(AppointmentDateTime.isValidDateTime("30-2-2025 1200"));
        // Missing HHmm
        assertFalse(AppointmentDateTime.isValidDateTime("12-13-2024"));

        // correct dd-MM-yyyy correct HHmm
        assertTrue(AppointmentDateTime.isValidDateTime("12-3-2024 1100"));
        // correct dd-M-yyyy correct HHmm
        assertTrue(AppointmentDateTime.isValidDateTime("12-3-2024 1100"));
        //correct d-MM-yyyy correct HHmm
        assertTrue(AppointmentDateTime.isValidDateTime("2-11-2024 1900"));
        //correct d-M-yyyy correct HHmm
        assertTrue(AppointmentDateTime.isValidDateTime("2-3-2024 1900"));
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
