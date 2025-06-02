package Settings;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoadSettings {

    public static void checkExistingSettings(){

        File f = new File(SettingsData.settings);
         if(!f.exists()){
            System.out.println("Didnt found any settings. They will be automatically set to default.");
            SaveSettings.saveSettings();
         } else {
            System.out.println("Settings have been uploaded");
            loadSettings();
         }
    }

    public static void loadSettings() {
         try (Scanner fs = new Scanner(new File("settings.txt"))) {
            SettingsData.firstPlayer = fs.nextLine();
            SettingsData.secondPlayer = fs.nextLine();
            SettingsData.boardSize = Integer.parseInt(fs.nextLine());
        } catch (IOException e) {
            System.out.println("Woopsy file already occured.");
        }
    }
}
