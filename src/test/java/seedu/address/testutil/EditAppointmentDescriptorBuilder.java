package seedu.address.testutil;

import seedu.address.logic.commands.LinkAppointmentEditCommand.EditAppointmentDescriptor;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentLength;
import seedu.address.model.appointment.AppointmentLocation;
import seedu.address.model.appointment.AppointmentMessage;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.appointment.AppointmentType;

/**
 * A utility class to help with building EditAppointmentDescriptor objects.
 */
public class EditAppointmentDescriptorBuilder {
    private EditAppointmentDescriptor descriptor;

    public EditAppointmentDescriptorBuilder() {
        descriptor = new EditAppointmentDescriptor();
    }

    public EditAppointmentDescriptorBuilder(EditAppointmentDescriptor descriptor) {
        this.descriptor = new EditAppointmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditAppointmentDescriptor} with fields containing {@code appointment}'s details
     */
    public EditAppointmentDescriptorBuilder(Appointment appointment) {
        descriptor = new EditAppointmentDescriptor();
        descriptor.setId(appointment.getId());
        descriptor.setDateTime(appointment.getDateTime());
        descriptor.setLength(appointment.getLength());
        descriptor.setLocation(appointment.getLocation());
        descriptor.setType(appointment.getType());
        descriptor.setMessage(appointment.getMessage());
        descriptor.setStatus(appointment.getStatus());
    }

    /**
     * Sets the {@code Id} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withId(String id) {
        descriptor.setId(new AppointmentId(id));
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new AppointmentDateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code length} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withLength(String length) {
        descriptor.setLength(new AppointmentLength(length));
        return this;
    }

    /**
     * Sets the {@code location} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new AppointmentLocation(location));
        return this;
    }

    /**
     * Sets the {@code type} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withType(String type) {
        descriptor.setType(new AppointmentType(type));
        return this;
    }

    /**
     * Sets the {@code message} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withMessage(String message) {
        descriptor.setMessage(new AppointmentMessage(message));
        return this;
    }

    /**
     * Sets the {@code status} of the {@code EditAppointmentDescriptor} that we are building.
     */
    public EditAppointmentDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new AppointmentStatus(status));
        return this;
    }

    public EditAppointmentDescriptor build() {
        return descriptor;
    }
}
