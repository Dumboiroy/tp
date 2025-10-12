package seedu.address.model.appointment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Represents the ID of an appointment.
 * Format: any string.
 */
public class AppointmentId {
    public static final String NO_ID = "";
    private static final String ID_FILEPATH = "data/appt_ids.txt";
    private final String id;

    public AppointmentId() {
        this.id = generateId();
    }

    public AppointmentId(String id) {
        this.id = id;
    }

    private String generateId() {
        Path path = Paths.get(ID_FILEPATH);
        String tempId = UUID.randomUUID().toString().substring(0, 7);
        List<String> ids;
        try {
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
            Scanner fileIn = new Scanner(new File(String.valueOf(path)));
            ids = getAllIds(fileIn);
            while (ids.contains(tempId)) {
                tempId = UUID.randomUUID().toString().substring(0, 7);
            }
            save(path, tempId, ids);
            return tempId;
        } catch (IOException e) {
            System.out.println("Problem with creating file: " + e);
            return tempId;
        }
    }

    private void save(Path path, String tempId, List<String> ids) {
        String idsString = getStringToSave(tempId, ids);

        try {
            FileWriter myWriter = new FileWriter(String.valueOf(path));
            myWriter.write(idsString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error Saving New Task");
        }
    }

    private String getStringToSave(String tempId, List<String> ids) {
        StringBuilder str = new StringBuilder();

        for (String tempStr : ids) {
            str.append(tempStr).append("\n");
        }

        str.append(tempId);

        return str.toString();
    }

    private List<String> getAllIds(Scanner fileIn) {
        List<String> ids = new ArrayList<>();

        while (fileIn.hasNext()) {
            ids.add(fileIn.nextLine());
        }
        return ids;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AppointmentId) {
            return ((AppointmentId) other).id.equals(this.id);
        }
        return false;
    }
}
