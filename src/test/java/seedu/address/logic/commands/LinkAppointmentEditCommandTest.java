package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.LinkAppointmentEditCommand.EditAppointmentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class LinkAppointmentEditCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void resetModel() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_editDate_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor1 = new EditAppointmentDescriptorBuilder()
                .withDateTime("12-10-3099 1430")
                .build();

        Appointment newAppt1 = new Appointment(
                id,
                appt.getClientName(),
                descriptor1.getDateTime().orElse(appt.getDateTime()),
                descriptor1.getLength().orElse(appt.getLength()),
                descriptor1.getLocation().orElse(appt.getLocation()),
                descriptor1.getType().orElse(appt.getType()),
                descriptor1.getMessage().orElse(appt.getMessage()),
                descriptor1.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand1 = new LinkAppointmentEditCommand(id, descriptor1);

        String expectedMessage1 = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt1));

        assertEquals(expectedMessage1, editApptCommand1.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editDateTime_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor2 = new EditAppointmentDescriptorBuilder()
                .withDateTime("12-10-3099 0000")
                .build();

        Appointment newAppt2 = new Appointment(
                id,
                appt.getClientName(),
                descriptor2.getDateTime().orElse(appt.getDateTime()),
                descriptor2.getLength().orElse(appt.getLength()),
                descriptor2.getLocation().orElse(appt.getLocation()),
                descriptor2.getType().orElse(appt.getType()),
                descriptor2.getMessage().orElse(appt.getMessage()),
                descriptor2.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand2 = new LinkAppointmentEditCommand(id, descriptor2);

        String expectedMessage2 = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt2));

        assertEquals(expectedMessage2, editApptCommand2.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editLength_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withLength("30")
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editLocation_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withLocation("Home")
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editType_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withType("House-visit")
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editMessage_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withMessage("Test message 123")
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editStatus_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withStatus("Planned")
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_editStatusInvalid_failure() {
        assertThrows(IllegalArgumentException.class, () -> new EditAppointmentDescriptorBuilder()
                .withStatus("test")
                .build());
    }

    @Test
    public void execute_invalidId_failure() {
        AppointmentId id = new AppointmentId("dummy");

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withStatus("Planned")
                .build();

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);

        assertThrows(CommandException.class, () -> editApptCommand.execute(model));
    }

    @Test
    public void execute_multipleEdits_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withStatus("Planned")
                .withLocation("Home")
                .withDateTime("12-12-2025 1400")
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_sameOldNewAppointment_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDateTime(appt.getDateTime().toString())
                .withLength(appt.getLength().toString())
                .withLocation(appt.getLocation().toString())
                .withMessage(appt.getMessage().toString())
                .withType(appt.getType().toString())
                .withStatus(appt.getStatus().toString())
                .build();

        Appointment newAppt = new Appointment(
                id,
                appt.getClientName(),
                descriptor.getDateTime().orElse(appt.getDateTime()),
                descriptor.getLength().orElse(appt.getLength()),
                descriptor.getLocation().orElse(appt.getLocation()),
                descriptor.getType().orElse(appt.getType()),
                descriptor.getMessage().orElse(appt.getMessage()),
                descriptor.getStatus().orElse(appt.getStatus())
        );

        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        String expectedMessage = String.format(
                LinkAppointmentEditCommand.MESSAGE_SUCCESS,
                client.getName().toString(),
                Messages.format(newAppt));

        assertEquals(expectedMessage, editApptCommand.execute(model).getFeedbackToUser());
    }

    @Test
    public void isAnyFieldEdited_success() {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDateTime(appt.getDateTime().toString())
                .withLength(appt.getLength().toString())
                .withLocation(appt.getLocation().toString())
                .withMessage(appt.getMessage().toString())
                .withType(appt.getType().toString())
                .withStatus(appt.getStatus().toString())
                .build();

        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_failure() {
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder().build();
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void equals() {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDateTime(appt.getDateTime().toString())
                .withLength(appt.getLength().toString())
                .withLocation(appt.getLocation().toString())
                .withMessage(appt.getMessage().toString())
                .withType(appt.getType().toString())
                .withStatus(appt.getStatus().toString())
                .build();

        EditAppointmentDescriptor descriptor1 = new EditAppointmentDescriptorBuilder()
                .withDateTime(appt.getDateTime().toString())
                .withLength(appt.getLength().toString())
                .withLocation(appt.getLocation().toString())
                .withMessage(appt.getMessage().toString())
                .withType(appt.getType().toString())
                .withStatus(appt.getStatus().toString())
                .build();

        EditAppointmentDescriptor descriptor2 = new EditAppointmentDescriptorBuilder()
                .withDateTime(appt.getDateTime().toString())
                .withLength(appt.getLength().toString())
                .withLocation("Testing")
                .withMessage(appt.getMessage().toString())
                .withType(appt.getType().toString())
                .withStatus(appt.getStatus().toString())
                .build();


        LinkAppointmentCommand editApptCommand = new LinkAppointmentEditCommand(id, descriptor);
        LinkAppointmentCommand editApptCommand1 = new LinkAppointmentEditCommand(id, descriptor1);
        LinkAppointmentCommand editApptCommand2 = new LinkAppointmentEditCommand(id, descriptor2);

        assertTrue(editApptCommand.equals(editApptCommand));
        assertTrue(editApptCommand1.equals(editApptCommand));
        assertFalse(editApptCommand1.equals(editApptCommand2));
        assertFalse(editApptCommand1.equals(2));
    }
}
