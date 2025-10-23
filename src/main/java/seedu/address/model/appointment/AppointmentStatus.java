package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the status of an appointment.
 * Must be one of planned, confirmed, completed, cancelled.
 */
public class AppointmentStatus {

    public static final String MESSAGE_CONSTRAINTS =
        "Status must be one of: planned, confirmed, completed, cancelled";

    public final AppointmentStatusType value;

    /**
     * Constructs an {@code AppointmentStatus}.
     * If the given string is empty, defaults to PLANNED.
     */
    public AppointmentStatus(String status) {
        requireNonNull(status);
        String trimmedStatus = status.trim().toLowerCase();
        if (trimmedStatus.isEmpty()) {
            trimmedStatus = "planned";
        }
        checkArgument(isValidStatus(trimmedStatus), MESSAGE_CONSTRAINTS);
        this.value = stringToStatus(trimmedStatus);
    }

    /**
     * Converts a string to the corresponding enum type.
     */
    public static AppointmentStatusType stringToStatus(String input) {
        switch (input) {
        case "planned":
            return AppointmentStatusType.PLANNED;
        case "confirmed":
            return AppointmentStatusType.CONFIRMED;
        case "completed":
            return AppointmentStatusType.COMPLETED;
        case "cancelled":
            return AppointmentStatusType.CANCELLED;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid appointment status.
     */
    public static boolean isValidStatus(String test) {
        String trimmed = test.trim().toLowerCase();
        return trimmed.equals("planned")
                || trimmed.equals("confirmed")
                || trimmed.equals("completed")
                || trimmed.equals("cancelled");
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentStatus
                && value.equals(((AppointmentStatus) other).value));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
