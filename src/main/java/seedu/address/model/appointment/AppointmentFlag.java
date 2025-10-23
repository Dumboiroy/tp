package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the command flag of an appointment.
 * Must be one of c, d or e.
 */
public class AppointmentFlag {
    public static final String MESSAGE_CONSTRAINTS =
            "Flag must be either -c for create, -d for delete or -e for edit.";
    public final char value;

    /**
     * Creates an appointment status
     */
    public AppointmentFlag(char flag) {
        checkArgument(isValidFlag(flag), MESSAGE_CONSTRAINTS);
        this.value = flag;
    }

    /**
     * Checks if flag char is valid
     */
    public static boolean isValidFlag(char flag) {
        return flag == 'c' || flag == 'd' || flag == 'e';
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AppointmentFlag) {
            return ((AppointmentFlag) other).value == this.value;
        }
        return false;
    }
}
