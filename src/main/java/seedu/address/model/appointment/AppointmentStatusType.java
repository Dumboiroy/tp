package seedu.address.model.appointment;

/**
 * Contains all valid Appointment Status types.
 */
public enum AppointmentStatusType {
    PLANNED,
    CONFIRMED,
    COMPLETED,
    CANCELLED;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
