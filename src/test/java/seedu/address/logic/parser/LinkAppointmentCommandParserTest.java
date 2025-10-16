package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_DATE_TIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_LENGTH_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_MESSAGE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_STATUS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.APPOINTMENT_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CREATE_FLAG;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_FLAG;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_LENGTH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_LOCATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_MESSAGE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_STATUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_TYPE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LENGTH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LinkAppointmentCommand;
import seedu.address.logic.commands.LinkAppointmentCreateCommand;
import seedu.address.logic.commands.LinkAppointmentEditCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.AppointmentLength;
import seedu.address.model.appointment.AppointmentLocation;
import seedu.address.model.appointment.AppointmentMessage;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.appointment.AppointmentStatusType;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class LinkAppointmentCommandParserTest {
    private LinkAppointmentCommandParser parser = new LinkAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Appointment expectedAppointment = BOB.getAppointments().get(0);
        // whitespace only preamble
        String input = CREATE_FLAG + NAME_DESC_BOB + APPOINTMENT_DATE_TIME_DESC_BOB
                + APPOINTMENT_LENGTH_DESC_BOB
                + APPOINTMENT_LOCATION_DESC_BOB + APPOINTMENT_TYPE_DESC_BOB
                + APPOINTMENT_MESSAGE_DESC_BOB
                + APPOINTMENT_STATUS_DESC_BOB;
        assertParseSuccess(parser, input,
                new LinkAppointmentCreateCommand(BOB.getName(), expectedAppointment));
    }

    @Test
    public void parse_mandatoryFieldsPresent_success() {
        Appointment expectedAppointment = new Appointment(BOB.getName(),
                new AppointmentDateTime(VALID_APPOINTMENT_DATE_TIME), new AppointmentLength(""),
                new AppointmentLocation(""), new AppointmentType(""),
                new AppointmentMessage(""), new AppointmentStatus(AppointmentStatusType.PLANNED.toString()));
        // whitespace only preamble
        assertParseSuccess(parser, CREATE_FLAG + NAME_DESC_BOB
                        + APPOINTMENT_DATE_TIME_DESC_BOB,
                new LinkAppointmentCreateCommand(BOB.getName(), expectedAppointment));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                LinkAppointmentCommand.MESSAGE_USAGE);
        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + APPOINTMENT_DATE_TIME_DESC_BOB,
                expectedMessage);

        // missing appointment prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_APPOINTMENT_DATE_TIME,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_APPOINTMENT_DATE_TIME,
                expectedMessage);

        // Missing appointment timing
        assertParseFailure(parser, NAME_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void valid_setEditAppointmentDescriptor() throws ParseException {
        String input = EDIT_FLAG + NAME_DESC_BOB + APPOINTMENT_DATE_TIME_DESC_BOB
                + APPOINTMENT_LENGTH_DESC_BOB
                + APPOINTMENT_LOCATION_DESC_BOB + APPOINTMENT_TYPE_DESC_BOB
                + APPOINTMENT_MESSAGE_DESC_BOB
                + APPOINTMENT_STATUS_DESC_BOB;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(input,
                        PREFIX_FLAG, PREFIX_ID, PREFIX_NAME, PREFIX_APPOINTMENT, PREFIX_LENGTH,
                        PREFIX_LOCATION, PREFIX_TYPE, PREFIX_MESSAGE, PREFIX_STATUS);

        EditAppointmentDescriptor descriptor1 = new EditAppointmentDescriptorBuilder()
                .build();
        EditAppointmentDescriptor descriptor2 = new EditAppointmentDescriptorBuilder()
                .withDateTime(VALID_APPOINTMENT_DATE_TIME)
                .withLength(VALID_APPOINTMENT_LENGTH)
                .withLocation(VALID_APPOINTMENT_LOCATION)
                .withType(VALID_APPOINTMENT_TYPE)
                .withMessage(VALID_APPOINTMENT_MESSAGE)
                .withStatus(VALID_APPOINTMENT_STATUS)
                .build();

        new LinkAppointmentCommandParser().setEditAppointmentDescriptor(descriptor1, argMultimap);

        assertEquals(descriptor1, descriptor2);
    }
}
