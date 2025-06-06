package Statistic;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveStatistic {

    public static String statistic = "statistic.txt";
    public static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final LocalDateTime date = LocalDateTime.now();

    public static void saveStats(String winner, int size, String firstPlayer, String secondPlayer) {
        try (FileWriter fw = new FileWriter(statistic, true)) {
            fw.write("Winner: " + winner + "\n");
            fw.write("Date: " + date.format(format) + "\n");
            fw.write("Field size: " + size + "x" + size + "\n");
            fw.write("First Player: " + firstPlayer + "\n");
            fw.write("Second Player: " + secondPlayer + "\n");
        } catch (IOException e) {
            System.out.println("File already occured.");
        }
    }
    
}
