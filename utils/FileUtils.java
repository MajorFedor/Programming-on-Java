package utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {
    public static void saveFile(String filepath, LocalDateTime[] data, String[] notes, DateTimeFormatter formatter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            for (int i = 0; i < data.length; i++) {
                if (data[i] != null) {
                    writer.write(data[i].format(formatter));
                    writer.newLine();
                    writer.write(notes[i]);
                    writer.newLine();
                    writer.newLine();
                }
            }
            System.out.println("Diary saved.");
        } catch (IOException e) {
            System.out.println("Error saving diary.");
        }
    }

    public static void readFullFile(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int strCounter = 0;
            while ((line = reader.readLine()) != null) {
                strCounter++;
                System.out.println(strCounter + " " + line);
            }
        } catch (IOException e) {
            System.out.println("Some troubles. " + e.getMessage());
        }
    }
}
