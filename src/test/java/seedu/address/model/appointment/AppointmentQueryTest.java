package seedu.address.model.appointment;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.DENTIST_APPT;
import static seedu.address.testutil.TypicalPersons.MEETING_APPT;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AppointmentBuilder;


public class AppointmentQueryTest {
    @Test
    public void filter_empty_expectTrue() {
        assertTrue(AppointmentQuery.build().filter(MEETING_APPT));
    }

    @Test
    public void filter_byTimeToday_expectTrue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hhmm");

        // Format the current date and time
        String formattedDateTime = LocalDateTime.now()
                .format(formatter);

        Appointment appt = (new AppointmentBuilder(DENTIST_APPT))
                .withDateTime(formattedDateTime).build();
        assertTrue(AppointmentQuery.build()
                .setDateTime(AppointmentDateTimeQuery.today()).filter(appt));
    }

    @Test
    public void filter_byTimeToday_expectFalse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hhmm");

        // Format the current date and time
        String formattedDateTime = LocalDateTime.now().plusDays(2)
                .format(formatter);

        Appointment appt = (new AppointmentBuilder(DENTIST_APPT))
                .withDateTime(formattedDateTime).build();
        assertFalse(AppointmentQuery.build()
                .setDateTime(AppointmentDateTimeQuery.today()).filter(appt));
    }

    @Test
    public void filter_byTimeRelativeDays_expectTrue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hhmm");

        // Format the current date and time
        String formattedDateTime = LocalDateTime.now().plusDays(2)
                .format(formatter);

        Appointment appt = (new AppointmentBuilder(DENTIST_APPT))
                .withDateTime(formattedDateTime).build();
        assertTrue(AppointmentQuery.build()
                .setDateTime(AppointmentDateTimeQuery.withinRelativeDays(5))
                .filter(appt));
    }

    @Test
    public void filter_byTimeRelativeDays_expectFalse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hhmm");

        // Format the current date and time
        String formattedDateTime = LocalDateTime.now().plusDays(3)
                .format(formatter);

        Appointment appt = (new AppointmentBuilder(DENTIST_APPT))
                .withDateTime(formattedDateTime).build();
        assertFalse(AppointmentQuery.build()
                .setDateTime(AppointmentDateTimeQuery.withinRelativeDays(1))
                .filter(appt));
    }

    @Test
    public void filter_byTimeInterval_expectTrue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hhmm");

        // Format the current date and time
        String formattedDateTime = LocalDateTime.now().plusDays(2)
                .format(formatter);

        Appointment appt = (new AppointmentBuilder(DENTIST_APPT))
                .withDateTime(formattedDateTime).build();
        assertTrue(AppointmentQuery.build()
                .setDateTime(AppointmentDateTimeQuery.interval(
                        LocalDateTime.now(), Duration.ofDays(3)
                )).filter(appt));
    }

    @Test
    public void filter_byStatus_expectTrue() {
        assertTrue(AppointmentQuery.build()
                .setStatus(MEETING_APPT.getStatus())
                .filter(MEETING_APPT));
    }

    @Test
    public void filter_byStatus_expectFalse() {
        assertFalse(AppointmentQuery.build()
                .setStatus(new AppointmentStatus("cancelled"))
                .filter(MEETING_APPT));
    }

    @Test
    public void filter_byType_expectTrue() {
        assertTrue(AppointmentQuery.build()
                .setType(MEETING_APPT.getType())
                .filter(MEETING_APPT));
    }

    @Test
    public void filter_byType_expectFalse() {
        assertFalse(AppointmentQuery.build()
                .setType(new AppointmentType("dummy"))
                .filter(MEETING_APPT));
    }

    @Test
    public void equals() {
        AppointmentQuery first = AppointmentQuery.build();
        AppointmentQuery second = AppointmentQuery.build()
                .setType(new AppointmentType("dummy"));
        AppointmentQuery third = AppointmentQuery.build()
                .setType(new AppointmentType("dummy"));
        // compare same object -> returns true
        assertTrue(first.equals(first));
        // compare null -> returns false
        assertFalse(first.equals(null));
        // compare same query -> returns true
        assertTrue(second.equals(third));
        // compare different query -> returns false
        assertFalse(first.equals(second));
    }
}
