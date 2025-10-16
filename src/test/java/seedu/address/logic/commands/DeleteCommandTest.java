package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CommandTestUtil.showPersonWithName;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalNames.NAME_ALICE_PAULINE;
import static seedu.address.testutil.TypicalNames.NAME_BENSON_MEIER;
import static seedu.address.testutil.TypicalNames.NAME_DOES_NOT_EXIST;
import static seedu.address.testutil.TypicalPersons.ALICE_WITH_SUBSTRING_NAME;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name nameToDelete = personToDelete.getName();
        DeleteCommand deleteCommand = new DeleteCommand(nameToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(NAME_DOES_NOT_EXIST);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_DOES_NOT_EXIST);
    }

    @Test
    public void execute_validNameFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Name nameToDelete = personToDelete.getName();
        DeleteCommand deleteCommand = new DeleteCommand(nameToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSubstringNameFilteredList_success() {
        showPersonWithName(model, ALICE_WITH_SUBSTRING_NAME.getName());
        Person personToDelete = ALICE_WITH_SUBSTRING_NAME;
        Name nameToDelete = personToDelete.getName();
        DeleteCommand deleteCommand = new DeleteCommand(nameToDelete);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                Messages.format(personToDelete));

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Checks if a valid name that is not in the filtered list but is in the address
     * book still fails the command.
     */
    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        // update filtered list to show only the first person
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Name notInViewButInBookName = NAME_BENSON_MEIER;
        // ensures that notInViewButInBookName is still in address book list
        assertTrue(model.getAddressBook().getPersonList().stream()
                .anyMatch(p -> p.getName().equals(notInViewButInBookName)));
        // try to delete someone whose name is in the address book, but not in the
        // filtered list
        DeleteCommand deleteCommand = new DeleteCommand(notInViewButInBookName);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(NAME_ALICE_PAULINE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(NAME_BENSON_MEIER);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(NAME_ALICE_PAULINE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        DeleteCommand deleteCommand = new DeleteCommand(NAME_ALICE_PAULINE);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetName=" + "Alice Pauline" + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
