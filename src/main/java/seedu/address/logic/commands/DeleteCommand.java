package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

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

    private static final Logger logger = Logger.getLogger("DeleteCommand");

    private final Name targetName;

    /**
     * Creates a DeleteCommand to delete the specified {@code Person} by exact {@code Name}.
     */
    public DeleteCommand(Name nameToDelete) {
        requireNonNull(nameToDelete);
        this.targetName = nameToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // get person from nameQuery - i.e. get person from lastShownList whose name matches nameToDelete
        List<Person> matchedPersons = getMatchedPerson(lastShownList, targetName);
        requireNonEmptyPersonList(matchedPersons);
        requireSinglePersonList(matchedPersons);

        // At this point, matchedPersons should have exactly one person.
        assert matchedPersons.size() == 1;

        // Require person to be in filtered list
        requirePersonInFilteredList(model, matchedPersons.get(0));

        // delete person
        deletePersonsFromList(model, matchedPersons);
        // for now, the message below supports a single deletion only.
        Person deletedPerson = matchedPersons.get(0);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(deletedPerson)));
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

    private static List<Person> getMatchedPerson(List<Person> personList, Name targetName) {
        return personList.stream()
                .filter(person -> person.getName().equals(targetName))
                .toList();
    }

    private static void requireNonEmptyPersonList(List<Person> personList) throws CommandException {
        // Check for empty person list
        if (personList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_PERSON_DOES_NOT_EXIST);
        }
    }

    private static void requireSinglePersonList(List<Person> personList) throws CommandException {
        if (personList.size() > 1) {
            String[] names = personList.stream()
                    .map(person -> person.getName().fullName)
                    .toArray(String[]::new);
            throw new CommandException(Messages.MESSAGE_MULTIPLE_PERSONS_FOUND_NAME + String.join(", ", names));
        }
    }

    private static void requirePersonInModel(Model model, Person person) throws CommandException {
        if (!model.hasPerson(person)) {
            throw new CommandException(Messages.MESSAGE_PERSON_DOES_NOT_EXIST);
        }
    }

    private static void requirePersonInFilteredList(Model model, Person person) throws CommandException {
        List<Person> filteredList = model.getFilteredPersonList();
        if (!filteredList.contains(person)) {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_IN_FILTERED_LIST);
        }
    }

    private static void deletePersonsFromList(Model model, List<Person> personList) throws CommandException {
        for (Person personToDelete : personList) {
            requireNonNull(personToDelete);
            requirePersonInModel(model, personToDelete);
            logger.info("Deleting person: " + personToDelete);
            model.deletePerson(personToDelete);
        }
    }
}
