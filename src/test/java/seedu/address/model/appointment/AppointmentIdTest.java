package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentIdTest {
    private static final String ID_FILEPATH = "src/test/data/ConfigUtilTest/appt_ids.txt";

    @BeforeEach
    public void clearData() {
        Path path = Paths.get(ID_FILEPATH);

        try {
            FileWriter myWriter = new FileWriter(String.valueOf(path));
            myWriter.write("");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error Resetting");
        }
    }

    @Test
    public void random_id_constructor() {
        AppointmentId id = new AppointmentId();
        assertNotNull(id.toString());
        assertEquals(7, id.toString().length());
    }

    @Test
    public void fixed_id_constructor() {
        String idStr = "testing";
        AppointmentId id = new AppointmentId(idStr);
        assertEquals(idStr, id.toString());
    }

    @Test
    public void generateId() {
        List<String> idList = new ArrayList<>();
        AppointmentId dummyApptId = new AppointmentId("dummy");

        for (int i = 0; i < 999; i++) {
            idList.add(dummyApptId.generateId(ID_FILEPATH));
        }

        assertEquals(999, idList.size());
    }

    @Test
    public void correct_toString() {
        AppointmentId dummyApptId = new AppointmentId("dummy");
        AppointmentId randomApptId = new AppointmentId();
        assertEquals("dummy", dummyApptId.toString());
        assertEquals(7, randomApptId.toString().length());
    }

    @Test
    public void equals() {
        AppointmentId random1 = new AppointmentId();
        AppointmentId random2 = new AppointmentId();
        AppointmentId dummy1 = new AppointmentId("dummy");
        AppointmentId dummy2 = new AppointmentId("dummy");

        assertEquals(dummy1, dummy2);
        assertNotEquals(random1, random2);
    }
}
