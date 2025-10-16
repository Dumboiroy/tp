package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LENGTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Superclass for all Link commands
 */
public abstract class LinkAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "link";
    public static final String MESSAGE_EDIT_APPOINTMENT = COMMAND_WORD + " -e "
            + PREFIX_ID + "123456 "
            + PREFIX_NAME + "Alex Wu "
            + PREFIX_APPOINTMENT + "12-10-2025 1430 "
            + PREFIX_LENGTH + "90 "
            + PREFIX_LOCATION + "Bukit Merah FSC "
            + PREFIX_TYPE + "home-visit "
            + PREFIX_MESSAGE + "Bring consent form "
            + PREFIX_STATUS + "planned";
    public static final String MESSAGE_ADD_APPOINTMENT = COMMAND_WORD + " -c "
            + PREFIX_NAME + "Alex Wu "
            + PREFIX_APPOINTMENT + "12-10-2025 1430 "
            + PREFIX_LENGTH + "90 "
            + PREFIX_LOCATION + "Bukit Merah FSC "
            + PREFIX_TYPE + "home-visit "
            + PREFIX_MESSAGE + "Bring consent form "
            + PREFIX_STATUS + "planned";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Links a new appointment to a client. "
            + "Parameters: "
            + "FLAG "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_ID + "ID] "
            + PREFIX_APPOINTMENT + "DATE [TIME] "
            + "[" + PREFIX_LENGTH + "MINUTES] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_TYPE + "TYPE] "
            + "[" + PREFIX_MESSAGE + "NOTES] "
            + "[" + PREFIX_STATUS + "planned|confirmed|completed|cancelled]\n"
            + "Example:\n"
            + MESSAGE_ADD_APPOINTMENT + "\n"
            + MESSAGE_EDIT_APPOINTMENT;
    public static final String MESSAGE_SUCCESS = "New appointment linked to %1$s: %2$s";
    public static final String MESSAGE_NO_SUCH_PERSON = "No client found with the name: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENTS = "This appointment already exists in the address book.";
    public static final String MESSAGE_INVALID_EDIT_SYNTAX = "Invalid Edit syntax. ";

    @Override
    public abstract CommandResult execute(Model model) throws CommandException;

    @Override
    public abstract boolean equals(Object other);
}
