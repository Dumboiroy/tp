package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentFlagTest {
    @Test
    public void create_appointmentFlag_success() {
        AppointmentFlag flag1 = new AppointmentFlag('c');
        AppointmentFlag flag2 = new AppointmentFlag('d');
        AppointmentFlag flag3 = new AppointmentFlag('e');

        assertNotNull(flag1);
        assertNotNull(flag2);
        assertNotNull(flag3);
    }

    @Test
    public void create_appointmentFlag_fail() {
        char[] invalidFlags = new char[]
            {'a', 'b', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (char i : invalidFlags) {
            assertThrows(IllegalArgumentException.class, () -> new AppointmentFlag(i));
        }
    }

    @Test
    public void equals() {
        AppointmentFlag flag1 = new AppointmentFlag('c');
        AppointmentFlag flag2 = new AppointmentFlag('d');
        AppointmentFlag flag3 = new AppointmentFlag('d');

        assertEquals(flag2, flag3);
        assertNotEquals(flag1, flag2);
    }
}
