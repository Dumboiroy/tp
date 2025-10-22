package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.DENTIST_APPT;
import static seedu.address.testutil.TypicalPersons.MEETING_APPT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.testutil.AppointmentBuilder;

public class UniqueAppointmentListTest {
    private final UniqueAppointmentList uniqueAppointmentList = new UniqueAppointmentList();

    @Test
    public void contains_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.contains(null));
    }

    @Test
    public void contains_appointmentNotInList_returnsFalse() {
        assertFalse(uniqueAppointmentList.contains(DENTIST_APPT));
    }

    @Test
    public void contains_appointmentInList_returnsTrue() {
        uniqueAppointmentList.add(DENTIST_APPT);
        assertTrue(uniqueAppointmentList.contains(DENTIST_APPT));
    }

    @Test
    public void contains_appointmentWithSameClient_returnsTrue() {
        uniqueAppointmentList.add(DENTIST_APPT);
        uniqueAppointmentList.add(new AppointmentBuilder(DENTIST_APPT)
            .withDateTime("12-01-3000 1400").build());
        assertTrue(uniqueAppointmentList.contains(DENTIST_APPT));
    }

    @Test
    public void add_duplicateAppointment_returnsTrue() {
        uniqueAppointmentList.add(DENTIST_APPT);
        Appointment copied = new AppointmentBuilder(DENTIST_APPT).build();
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList.add(copied));
    }

    @Test
    public void setAppointment_nullTargetAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointment(null, DENTIST_APPT));
    }

    @Test
    public void setAppointment_targetNotInList_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList
            .setAppointment(DENTIST_APPT, DENTIST_APPT));
    }

    @Test
    public void setAppointment_editedAppointmentIsSameAppointment_success() {
        uniqueAppointmentList.add(DENTIST_APPT);
        uniqueAppointmentList.setAppointment(DENTIST_APPT, DENTIST_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(DENTIST_APPT);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasSameIdentity_success() {
        uniqueAppointmentList.add(DENTIST_APPT);
        Appointment editedDentistAppt = new AppointmentBuilder(DENTIST_APPT)
            .withLocation("Dental Clinic, Orchard")
            .withMessage("Bring insurance card")
            .build();
        uniqueAppointmentList.setAppointment(DENTIST_APPT, editedDentistAppt);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(editedDentistAppt);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasDifferentIdentity_success() {
        uniqueAppointmentList.add(DENTIST_APPT);
        uniqueAppointmentList.setAppointment(DENTIST_APPT, MEETING_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEETING_APPT);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointment_editedAppointmentHasNonUniqueIdentity_throwsDuplicateAppointmentException() {
        uniqueAppointmentList.add(DENTIST_APPT);
        uniqueAppointmentList.add(MEETING_APPT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList
            .setAppointment(DENTIST_APPT, MEETING_APPT));
    }

    @Test
    public void remove_nullAppointment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.remove(null));
    }

    @Test
    public void remove_appointmentDoesNotExist_throwsAppointmentNotFoundException() {
        assertThrows(AppointmentNotFoundException.class, () -> uniqueAppointmentList.remove(DENTIST_APPT));
    }

    @Test
    public void remove_existingAppointment_removesAppointment() {
        uniqueAppointmentList.add(DENTIST_APPT);
        uniqueAppointmentList.remove(DENTIST_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullUniqueAppointmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList
            .setAppointments((UniqueAppointmentList) null));
    }

    @Test
    public void setAppointments_uniqueAppointmentList_replacesOwnListWithProvidedUniqueAppointmentList() {
        uniqueAppointmentList.add(DENTIST_APPT);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEETING_APPT);
        uniqueAppointmentList.setAppointments(expectedUniqueAppointmentList);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAppointmentList.setAppointments((List<Appointment>) null));
    }

    @Test
    public void setAppointments_list_replacesOwnListWithProvidedList() {
        uniqueAppointmentList.add(DENTIST_APPT);
        List<Appointment> appointmentList = Collections.singletonList(MEETING_APPT);
        uniqueAppointmentList.setAppointments(appointmentList);
        UniqueAppointmentList expectedUniqueAppointmentList = new UniqueAppointmentList();
        expectedUniqueAppointmentList.add(MEETING_APPT);
        assertEquals(expectedUniqueAppointmentList, uniqueAppointmentList);
    }

    @Test
    public void setAppointments_listWithDuplicateAppointments_throwsDuplicateAppointmentException() {
        List<Appointment> listWithDuplicateAppointments = Arrays.asList(DENTIST_APPT, DENTIST_APPT);
        assertThrows(DuplicateAppointmentException.class, () -> uniqueAppointmentList
            .setAppointments(listWithDuplicateAppointments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueAppointmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAppointmentList.asUnmodifiableObservableList().toString(),
            uniqueAppointmentList.toString());
    }
}
