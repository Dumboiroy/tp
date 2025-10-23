package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Adapted from RankTest class.
 */
public class AppointmentStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentStatus(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "delayed";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentStatus(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null status
        assertThrows(NullPointerException.class, () -> AppointmentStatus.isValidStatus(null));

        // empty or whitespace defaults to planned
        assertTrue(AppointmentStatusType.PLANNED.equals(new AppointmentStatus("").value));
        assertTrue(AppointmentStatusType.PLANNED.equals(new AppointmentStatus("   ").value));
    }

    @Test
    public void isCaseInsensitive() {
        assertTrue(AppointmentStatus.isValidStatus("PlANned"));
        assertTrue(AppointmentStatus.isValidStatus("CONFirmed"));
        assertTrue(AppointmentStatus.isValidStatus("Completed"));
        assertTrue(AppointmentStatus.isValidStatus("CANcelled"));
    }
}
