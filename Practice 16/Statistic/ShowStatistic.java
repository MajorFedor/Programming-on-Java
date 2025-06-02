package Statistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ShowStatistic {
    public static void showStats(){
        File f = new File(SaveStatistic.statistic);
        if(!f.exists()){
            System.out.println("Play 1 game to show statistic");
            return;
        }

        try (BufferedReader fr = new BufferedReader(new FileReader(SaveStatistic.statistic))) {
            String line;

            while((line = fr.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Oops, file already occured.");
        }
    }
}
