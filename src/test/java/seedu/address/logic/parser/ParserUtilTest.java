package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.AppointmentFlag;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentLength;
import seedu.address.model.appointment.AppointmentLocation;
import seedu.address.model.appointment.AppointmentMessage;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_FLAG_1 = "cd";
    private static final String INVALID_FLAG_2 = "z";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = "*!blk 5";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_RANK = "high";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "92345678";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_RANK = "urgent";
    private static final String VALID_APPOINTMENT_FLAG = "c";
    private static final String VALID_APPOINTMENT_DATE_TIME = "20-10-2025 1400";
    private static final String VALID_APPOINTMENT_LENGTH = "60";
    private static final String VALID_APPOINTMENT_LOCATION = "Dental Clinic";
    private static final String VALID_APPOINTMENT_MESSAGE = "Routine checkup";
    private static final String VALID_APPOINTMENT_STATUS = "planned";
    private static final String VALID_APPOINTMENT_TYPE = "Health";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseRank_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseRank((String) null));
    }

    @Test
    public void parseRank_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseRank(INVALID_RANK));
    }

    @Test
    public void parseAppointmentDateTime_validValueWithoutWhitespace_returnsAppointmentDateTime() throws Exception {
        AppointmentDateTime expectedDateTime = new AppointmentDateTime(VALID_APPOINTMENT_DATE_TIME);
        assertEquals(expectedDateTime, ParserUtil.parseAppointmentDateTime(VALID_APPOINTMENT_DATE_TIME));
    }

    @Test
    public void parseAppointmentDateTime_validValueWithWhitespace_returnsTrimmedAppointmentDateTime() throws Exception {
        String dateTimeWithWhitespace = WHITESPACE + VALID_APPOINTMENT_DATE_TIME + WHITESPACE;
        AppointmentDateTime expectedDateTime = new AppointmentDateTime(VALID_APPOINTMENT_DATE_TIME);
        assertEquals(expectedDateTime, ParserUtil.parseAppointmentDateTime(dateTimeWithWhitespace));
    }

    @Test
    public void parseAppointmentLength_validValueWithoutWhitespace_returnsAppointmentLength() throws Exception {
        AppointmentLength expectedLength = new AppointmentLength(VALID_APPOINTMENT_LENGTH);
        assertEquals(expectedLength, ParserUtil.parseAppointmentLength(VALID_APPOINTMENT_LENGTH));
    }

    @Test
    public void parseAppointmentLength_validValueWithWhitespace_returnsTrimmedAppointmentLength() throws Exception {
        String lengthWithWhitespace = WHITESPACE + VALID_APPOINTMENT_LENGTH + WHITESPACE;
        AppointmentLength expectedLength = new AppointmentLength(VALID_APPOINTMENT_LENGTH);
        assertEquals(expectedLength, ParserUtil.parseAppointmentLength(lengthWithWhitespace));
    }

    @Test
    public void parseAppointmentLocation_validValueWithoutWhitespace_returnsAppointmentLocation() throws Exception {
        AppointmentLocation expectedLocation = new AppointmentLocation(VALID_APPOINTMENT_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseAppointmentLocation(VALID_APPOINTMENT_LOCATION));
    }

    @Test
    public void parseAppointmentLocation_validValueWithWhitespace_returnsTrimmedAppointmentLocation() throws Exception {
        String locationWithWhitespace = WHITESPACE + VALID_APPOINTMENT_LOCATION + WHITESPACE;
        AppointmentLocation expectedLocation = new AppointmentLocation(VALID_APPOINTMENT_LOCATION);
        assertEquals(expectedLocation, ParserUtil.parseAppointmentLocation(locationWithWhitespace));
    }

    @Test
    public void parseAppointmentType_validValueWithoutWhitespace_returnsAppointmentType() throws Exception {
        AppointmentType expectedType = new AppointmentType(VALID_APPOINTMENT_TYPE);
        assertEquals(expectedType, ParserUtil.parseAppointmentType(VALID_APPOINTMENT_TYPE));
    }

    @Test
    public void parseAppointmentType_validValueWithWhitespace_returnsTrimmedAppointmentType() throws Exception {
        String typeWithWhitespace = WHITESPACE + VALID_APPOINTMENT_TYPE + WHITESPACE;
        AppointmentType expectedType = new AppointmentType(VALID_APPOINTMENT_TYPE);
        assertEquals(expectedType, ParserUtil.parseAppointmentType(typeWithWhitespace));
    }

    @Test
    public void parseAppointmentMessage_validValueWithoutWhitespace_returnsAppointmentMessage() throws Exception {
        AppointmentMessage expectedMessage = new AppointmentMessage(VALID_APPOINTMENT_MESSAGE);
        assertEquals(expectedMessage, ParserUtil.parseAppointmentMessage(VALID_APPOINTMENT_MESSAGE));
    }

    @Test
    public void parseAppointmentMessage_validValueWithWhitespace_returnsTrimmedAppointmentMessage() throws Exception {
        String messageWithWhitespace = WHITESPACE + VALID_APPOINTMENT_MESSAGE + WHITESPACE;
        AppointmentMessage expectedMessage = new AppointmentMessage(VALID_APPOINTMENT_MESSAGE);
        assertEquals(expectedMessage, ParserUtil.parseAppointmentMessage(messageWithWhitespace));
    }

    @Test
    public void parseAppointmentStatus_validValueWithoutWhitespace_returnsAppointmentStatus() throws Exception {
        AppointmentStatus expectedStatus = new AppointmentStatus(VALID_APPOINTMENT_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseAppointmentStatus(VALID_APPOINTMENT_STATUS));
    }

    @Test
    public void parseAppointmentStatus_validValueWithWhitespace_returnsTrimmedAppointmentStatus() throws Exception {
        String statusWithWhitespace = WHITESPACE + VALID_APPOINTMENT_STATUS + WHITESPACE;
        AppointmentStatus expectedStatus = new AppointmentStatus(VALID_APPOINTMENT_STATUS);
        assertEquals(expectedStatus, ParserUtil.parseAppointmentStatus(statusWithWhitespace));
    }

    @Test
    public void parseAppointmentFlag_withValidFlag() throws Exception {
        AppointmentFlag expectedFlag = ParserUtil.parseAppointmentFlag(VALID_APPOINTMENT_FLAG);
        assertEquals(expectedFlag, new AppointmentFlag(VALID_APPOINTMENT_FLAG.charAt(0)));
    }

    @Test
    public void parseAppointmentFlag_withInvalidFlag() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentFlag(INVALID_FLAG_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentFlag(INVALID_FLAG_2));
    }

    @Test
    public void parseAppointmentId_withValidId() throws Exception {
        AppointmentId expectedId = ParserUtil.parseAppointmentId("dummy");
        assertEquals(new AppointmentId("dummy"), expectedId);
    }

    @Test
    public void parseAppointmentId_withInvalidId() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentId(""));
        assertThrows(ParseException.class, () -> ParserUtil.parseAppointmentId("   "));
    }
}
