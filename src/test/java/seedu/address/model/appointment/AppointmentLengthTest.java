package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AppointmentLengthTest {

    @Test
    public void isValidLength() {
        // Empty length -> false
        assertFalse(AppointmentLength.isValidLength(""));
        // Non numeric -> false
        assertFalse(AppointmentLength.isValidLength("abc"));
        // Negative integer -> false
        assertFalse(AppointmentLength.isValidLength("-30"));
        // Zero -> false
        assertFalse(AppointmentLength.isValidLength("0"));
        // Positive integer -> true
        assertTrue(AppointmentLength.isValidLength("20"));
        // Integer with trailing non-numeric characters -> false
        assertFalse(AppointmentLength.isValidLength("30."));
    }

    @Test
    public void equals() {
        AppointmentLength first = new AppointmentLength("30");
        AppointmentLength second = new AppointmentLength("30");
        AppointmentLength third = new AppointmentLength("40");
        assertTrue(first.equals(first));
        assertTrue(first.equals(second));
        assertFalse(first.equals(third));
    }

    @Test
    public void toStringMethod() {
        AppointmentLength first = new AppointmentLength("20");
        assertTrue(first.toString().equals("20"));
    }
}
