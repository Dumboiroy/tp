package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LENGTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RANK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameMatchesQueryNamePredicate;
import seedu.address.model.person.Person;
import seedu.address.model.rank.RankType;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_AMY_SUBSTRING = "Amy B";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "98765412";
    public static final String VALID_PHONE_BOB = "82222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_RANK_CRISIS = RankType.CRISIS.toString();
    public static final String VALID_RANK_STABLE = RankType.STABLE.toString();
    public static final String VALID_RANK_URGENT = RankType.URGENT.toString();
    public static final String VALID_RANK_VULNERABLE = RankType.VULNERABLE.toString();
    public static final String VALID_APPOINTMENT_ID = "3";
    public static final String VALID_APPOINTMENT_DATE_TIME = "20-10-2025 1400";
    public static final String VALID_APPOINTMENT_LENGTH = "60";
    public static final String VALID_APPOINTMENT_LOCATION = "Dental Clinic";
    public static final String VALID_APPOINTMENT_TYPE = "Health";
    public static final String VALID_APPOINTMENT_MESSAGE = "Routine checkup";
    public static final String VALID_APPOINTMENT_STATUS = "planned";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String RANK_DESC_CRISIS = " " + PREFIX_RANK + VALID_RANK_CRISIS;
    public static final String RANK_DESC_STABLE = " " + PREFIX_RANK + VALID_RANK_STABLE;
    public static final String RANK_DESC_URGENT = " " + PREFIX_RANK + VALID_RANK_URGENT;
    public static final String RANK_DESC_VULNERABLE = " " + PREFIX_RANK + VALID_RANK_VULNERABLE;

    // Appointments
    public static final String APPOINTMENT_ID_DESC_BOB = " " + PREFIX_ID + VALID_APPOINTMENT_ID;
    public static final String APPOINTMENT_DATE_TIME_DESC_BOB = " " + PREFIX_APPOINTMENT + VALID_APPOINTMENT_DATE_TIME;
    public static final String APPOINTMENT_LENGTH_DESC_BOB = " " + PREFIX_LENGTH + VALID_APPOINTMENT_LENGTH;
    public static final String APPOINTMENT_LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_APPOINTMENT_LOCATION;
    public static final String APPOINTMENT_TYPE_DESC_BOB = " " + PREFIX_TYPE + VALID_APPOINTMENT_TYPE;
    public static final String APPOINTMENT_MESSAGE_DESC_BOB = " " + PREFIX_MESSAGE + VALID_APPOINTMENT_MESSAGE;
    public static final String APPOINTMENT_STATUS_DESC_BOB = " " + PREFIX_STATUS + VALID_APPOINTMENT_STATUS;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " "; // empty names not allowed
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    // &^* not allowed in addresses
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + "Blk 5 &^*";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_RANK_DESC = " " + PREFIX_RANK + "high"; //only 4 words are allowed

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String CREATE_FLAG = " -c";
    public static final String EDIT_FLAG = " -e";
    public static final String DELETE_FLAG = " -d";


    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withRank(VALID_RANK_STABLE).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        model.updateFilteredPersonList(new NameMatchesQueryNamePredicate(person.getName()));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person with the given {@code targetName} in the
     * {@code model}'s address book.
     */
    public static void showPersonWithName(Model model, Name targetName) {
        assertTrue(model.hasPerson(
                model.getAddressBook().getPersonList().stream()
                        .filter(person -> person.getName().equals(targetName))
                        .findFirst().orElse(null)
        ));

        model.updateFilteredPersonList(new Predicate<Person>() {
            @Override
            public boolean test(Person p) {
                return p.getName().equals(targetName);
            }
        });

        assertEquals(1, model.getFilteredPersonList().size());
    }
}
