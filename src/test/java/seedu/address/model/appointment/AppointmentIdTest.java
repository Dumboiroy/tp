package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public void fixed_id_constructor() {
        String idStr = "testing";
        AppointmentId id = new AppointmentId(idStr);
        assertEquals(idStr, id.toString());
    }

    @Test
    public void correct_toString() {
        AppointmentId dummyApptId = new AppointmentId("dummy");
        assertEquals("dummy", dummyApptId.toString());
    }

    @Test
    public void equals() {
        AppointmentId dummy1 = new AppointmentId("dummy");
        AppointmentId dummy2 = new AppointmentId("dummy");

        assertEquals(dummy1, dummy2);
    }
}
