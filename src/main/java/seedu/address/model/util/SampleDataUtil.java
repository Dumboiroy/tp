package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.AppointmentId;
import seedu.address.model.appointment.AppointmentLength;
import seedu.address.model.appointment.AppointmentLocation;
import seedu.address.model.appointment.AppointmentMessage;
import seedu.address.model.appointment.AppointmentStatus;
import seedu.address.model.appointment.AppointmentType;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.rank.Rank;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data. <br/>
 * Update (10/8/2025): add {@code Appointment} field.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), new Rank("stable"),
                getSampleAppointmentsByIndices("Alex Yeoh", 2)),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends") , new Rank("stable"),
                getSampleAppointmentsByIndices("Bernice Yu", 1)),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours") , new Rank("stable"),
                getSampleAppointmentsByIndices("Charlotte Oliveiro", 0)),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), new Rank("urgent"),
                getSampleAppointmentsByIndices("David Li")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), new Rank("vulnerable"),
                getSampleAppointmentsByIndices("Irfan Ibrahim")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), new Rank("crisis"),
                getSampleAppointmentsByIndices("Roy Balakrishnan"))
        };
    }

    /**
     * Dispatches the list of appointments based on the given indices
     * @param indices variadic argument of target indices
     *                If {@code indices} is not specified, this method will return an
     *                empty list.
     */
    public static List<Appointment> getSampleAppointmentsByIndices(String clientName, int... indices) {
        List<Appointment> sampleAppointments = List.of(
            new Appointment(
                new AppointmentId("sampleid1"),
                new Name(clientName),
                new AppointmentDateTime("22-10-2025 0900"),
                new AppointmentLength("45"),
                new AppointmentLocation("Starbucks Jurong Point"),
                new AppointmentType("Casual"),
                new AppointmentMessage("Catch-up over coffee"),
                new AppointmentStatus("confirmed")
            ),
            new Appointment(
                new AppointmentId("sampleid2"),
                new Name(clientName),
                new AppointmentDateTime("23-10-2025 1600"),
                new AppointmentLength("120"),
                new AppointmentLocation("Zoom"),
                new AppointmentType("Remote"),
                new AppointmentMessage("Final presentation rehearsal"),
                new AppointmentStatus("cancelled")
            ),
            new Appointment(
                new AppointmentId("sampleid3"),
                new Name(clientName),
                new AppointmentDateTime("24-10-2025 1100"),
                new AppointmentLength("30"),
                new AppointmentLocation("NTUC Income Office"),
                new AppointmentType("Consultation"),
                new AppointmentMessage("Insurance policy review"),
                new AppointmentStatus("planned")
            ));
        List<Appointment> selected = new ArrayList<>();
        for (int index : indices) {
            if (index >= 0 && index < sampleAppointments.size()) {
                selected.add(sampleAppointments.get(index));
            }
        }
        return selected;
    }

    /**
     * Extract list of appointments from the given persons array
     */
    public static List<Appointment> extractAppointmentsFromPersons(Person[] persons) {
        return List.of(persons).stream()
            .flatMap(person -> person.getAppointments().stream())
            .toList();
    }

    /**
     * Load sample address book
     */
    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        Person[] samplePersons = getSamplePersons();
        for (Person samplePerson : samplePersons) {
            sampleAb.addPerson(samplePerson);
        }
        extractAppointmentsFromPersons(samplePersons)
            .forEach(sampleAb::addAppointment);
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
