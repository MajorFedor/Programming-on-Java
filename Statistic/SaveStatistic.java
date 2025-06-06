package Statistic;

import java.io.FileWriter;
import java.io.IOException;

public class SaveStatistic {

    public static String statistic = "statistic.txt";

    public static void saveStats() {
        try (FileWriter fw = new FileWriter(statistic, true)) {
            fw.write("Winner: " + StatisticData.winner + "\n");
            fw.write("Date: " + StatisticData.date + "\n");
            fw.write("Field size: " + StatisticData.size + "x" + StatisticData.size + "\n");
            fw.write("First Player: " + StatisticData.firstPlayer + "\n");
            fw.write("Second Player: " + StatisticData.secondPlayer + "\n");
        } catch (IOException e) {
            System.out.println("File already occured.");
        }
    }
    
}
