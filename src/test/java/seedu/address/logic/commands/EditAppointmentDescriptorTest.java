package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import seedu.address.logic.commands.LinkAppointmentEditCommand.EditAppointmentDescriptor;
import seedu.address.testutil.PersonBuilder;

import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

public class EditAppointmentDescriptorTest {
    private Person client1 = new PersonBuilder(ALICE).build();
    private Appointment appt1 = client1.getAppointments().get(0);
    private AppointmentId id1 = appt1.getId();

    private Person client2 = new PersonBuilder(BOB).build();
    private Appointment appt2 = client2.getAppointments().get(0);
    private AppointmentId id2 = appt2.getId();

    private EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
            .withDateTime(appt1.getDateTime().toString())
            .withLength(appt1.getLength().toString())
            .withLocation(appt1.getLocation().toString())
            .withMessage(appt1.getMessage().toString())
            .withType(appt1.getType().toString())
            .withStatus(appt1.getStatus().toString())
            .build();

    @Test
    public void equals() {
        // same values -> returns true
        EditAppointmentDescriptor descriptorWithSameValues = new EditAppointmentDescriptor(descriptor);
        assertTrue(descriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        assertFalse(descriptor.equals(null));

        // different types -> returns false
        assertFalse(descriptor.equals(5));

        // different values -> returns false
        assertFalse(appt1.equals(appt2));

        // different dateTime -> returns false
        EditAppointmentDescriptor editedAppt1 = new EditAppointmentDescriptorBuilder(appt1)
                .withDateTime(appt2.getDateTime().toString()).build();
        assertFalse(appt1.equals(editedAppt1));

        // different length -> returns false
        editedAppt1 = new EditAppointmentDescriptorBuilder(appt1)
                .withLength(appt2.getLength().toString()).build();
        assertFalse(editedAppt1.equals(appt1));

        // different locations -> returns false
        editedAppt1 = new EditAppointmentDescriptorBuilder(appt1)
                .withLocation(appt2.getLocation().toString()).build();
        assertFalse(editedAppt1.equals(appt1));

        // different type -> returns false
        editedAppt1 = new EditAppointmentDescriptorBuilder(appt1)
                .withType(appt2.getType().toString()).build();
        assertFalse(editedAppt1.equals(appt1));

        // different message -> returns false
        editedAppt1 = new EditAppointmentDescriptorBuilder(appt1)
                .withMessage(appt2.getMessage().toString()).build();
        assertFalse(editedAppt1.equals(appt1));

        // different status -> returns false
        editedAppt1 = new EditAppointmentDescriptorBuilder(appt1)
                .withStatus(appt2.getStatus().toString()).build();
        assertFalse(editedAppt1.equals(appt1));
    }

    @Test
    public void toStringMethod() {
        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        String expected = EditAppointmentDescriptor.class.getCanonicalName() + "{dateTime="
                + editAppointmentDescriptor.getDateTime().orElse(null) + ", length="
                + editAppointmentDescriptor.getLength().orElse(null) + ", location="
                + editAppointmentDescriptor.getLocation().orElse(null) + ", type="
                + editAppointmentDescriptor.getType().orElse(null) + ", message="
                + editAppointmentDescriptor.getMessage().orElse(null) + ", status="
                + editAppointmentDescriptor.getStatus().orElse(null) +  "}";
        assertEquals(expected, editAppointmentDescriptor.toString());
    }
}
