package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

public class LinkAppointmentDeleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @BeforeEach
    public void resetModel() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_delete_appointment_success() throws CommandException {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        LinkAppointmentCommand deleteApptCommand1 = new LinkAppointmentDeleteCommand(id);

        String expectedMessage1 = String.format(
                LinkAppointmentDeleteCommand.MESSAGE_SUCCESS,
                client.getName().toString()
        );
        assertEquals(expectedMessage1, deleteApptCommand1.execute(model).getFeedbackToUser());
    }

    @Test
    public void execute_delete_appointment_fail_AppointmentNotFound() {
        AppointmentId invalidId = new AppointmentId();

        LinkAppointmentCommand deleteApptCommand1 = new LinkAppointmentDeleteCommand(invalidId);

        String expectedMessage1 = String.format(
                LinkAppointmentDeleteCommand.MESSAGE_APPOINTMENT_NOT_FOUND, invalidId.toString()
        );
        assertCommandFailure(deleteApptCommand1, model, expectedMessage1);
    }

    @Test
    public void equals() {
        Person client = new PersonBuilder(ALICE).build();
        Appointment appt = client.getAppointments().get(0);
        AppointmentId id = appt.getId();

        LinkAppointmentCommand deleteApptCommand1 = new LinkAppointmentDeleteCommand(id);
        LinkAppointmentCommand deleteApptCommand2 = new LinkAppointmentDeleteCommand(id);

        // same object -> returns true
        assertEquals(deleteApptCommand1, deleteApptCommand1);

        // same values -> returns true
        assertEquals(deleteApptCommand1, deleteApptCommand2);

        // different types -> returns false
        assertNotEquals(1, deleteApptCommand1);

        // null -> returns false
        assertNotEquals(null, deleteApptCommand1);

        // different object -> returns false
        assertNotEquals(deleteApptCommand1, 's');

        // different appointment -> returns false
        Person client2 = TypicalPersons.BENSON;
        Appointment appt2 = client2.getAppointments().get(0);
        AppointmentId id2 = appt2.getId();
        LinkAppointmentCommand deleteApptCommand3 = new LinkAppointmentDeleteCommand(id2);
        assertNotEquals(deleteApptCommand1, deleteApptCommand3);
    }
}
