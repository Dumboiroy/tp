package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the name used in the displayed person list.\n"
            + "Parameters: NAME \n"
            + "Example: " + COMMAND_WORD + " John Doe"
            + "Note: The name is case sensitive.";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final Name targetName;

    public DeleteCommand(Name nameToDelete) {
        this.targetName = nameToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // get person from nameQuery - i.e. get person from lastShownList whose name
        // matches nameToDelete
        List<Person> matchedPersons = lastShownList.stream()
                .filter(person -> person.getName().equals(targetName))
                .toList();
        if (matchedPersons.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_PERSON_DOES_NOT_EXIST);
        }

        if (matchedPersons.size() > 1) {
            // should never reach here since we don't allow duplicate names
            String[] names = matchedPersons.stream()
                    .map(person -> person.getName().fullName)
                    .toArray(String[]::new);
            throw new CommandException(Messages.MESSAGE_MULTIPLE_PERSONS_FOUND_NAME + String.join(", ", names));
        }

        Person personToDelete = matchedPersons.get(0);
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand otherDeleteCommand)) {
            return false;
        }

        return targetName.equals(otherDeleteCommand.targetName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
