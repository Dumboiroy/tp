package seedu.address.logic.commands;

import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Edits an appointment and links it directly to a client (person).
 */
public class LinkAppointmentDeleteCommand extends LinkAppointmentCommand {
    public static final String MESSAGE_APPOINTMENT_NOT_FOUND = "The appointment with id %1$s could not be found.";
    public static final String MESSAGE_SUCCESS = "Appointment with %1$s deleted.";

    private final AppointmentId targetId;

    /**
     * Constructor to create a LinkAppointmentEditCommand
     */
    public LinkAppointmentDeleteCommand(AppointmentId targetId) {
        this.targetId = targetId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Appointment> apptList = model.getFilteredAppointmentList();
        checkApptExists(targetId, apptList);
        Appointment apptToDelete = getAppt(targetId, apptList);
        Name clientName = apptToDelete.getClientName();
        Optional<Person> clientOpt = model.getFilteredPersonList().stream()
                .filter(p -> {
                    assert clientName != null;
                    return p.getName().fullName.equalsIgnoreCase(clientName.fullName);
                })
                .findFirst();

        if (clientOpt.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_PERSON, clientName));
        }

        Person client = clientOpt.get();
        model.unsetAppointmentWithPerson(apptToDelete, client);
        model.updateFilteredAppointmentList(Model.PREDICATE_SHOW_ALL_APPOINTMENTS);

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, apptToDelete.getClientName().toString(), Messages.format(apptToDelete))
        );
    }

    private Appointment getAppt(AppointmentId id, ObservableList<Appointment> apptList) throws CommandException {
        for (Appointment appt : apptList) {
            if (appt.getId().equals(id)) {
                return appt;
            }
        }
        throw new CommandException("Unable to find appointment with ID: " + id);
    }

    private void checkApptExists(AppointmentId targetId, ObservableList<Appointment> apptList) throws CommandException {
        for (Appointment appt : apptList) {
            if (appt.getId().equals(targetId)) {
                return;
            }
        }
        throw new CommandException(String.format(MESSAGE_APPOINTMENT_NOT_FOUND, targetId.toString()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LinkAppointmentDeleteCommand)) {
            return false;
        }

        LinkAppointmentDeleteCommand otherCommand = (LinkAppointmentDeleteCommand) other;
        return otherCommand.targetId.equals(targetId);
    }
}
