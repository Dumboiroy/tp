package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Adapted from RankTypeTest class.
 */
public class AppointmentStatusTypeTest {

    @Test
    public void isCaseInsensitive() {
        assertTrue(AppointmentStatusType.PLANNED.equals(new AppointmentStatus("PlANned").value));
        assertTrue(AppointmentStatusType.CONFIRMED.equals(new AppointmentStatus("CONFirmed").value));
        assertTrue(AppointmentStatusType.COMPLETED.equals(new AppointmentStatus("ComplETed").value));
        assertTrue(AppointmentStatusType.CANCELLED.equals(new AppointmentStatus("CANcelled").value));
    }

    @Test
    public void invalidStatusType() {
        assertThrows(IllegalArgumentException.class, () -> AppointmentStatus.stringToStatus("plan"));
        assertThrows(IllegalArgumentException.class, () -> AppointmentStatus.stringToStatus("confirm"));
        assertThrows(IllegalArgumentException.class, () -> AppointmentStatus.stringToStatus("done"));
        assertThrows(IllegalArgumentException.class, () -> AppointmentStatus.stringToStatus("cancel"));
    }
}
