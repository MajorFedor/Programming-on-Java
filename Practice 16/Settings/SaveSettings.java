package Settings;

import java.io.FileWriter;
import java.io.IOException;

public class SaveSettings {
    public static void saveSettings() {

        String size = String.valueOf(SettingsData.boardSize);
        try (FileWriter fw = new FileWriter(SettingsData.settings)) {
            fw.write(SettingsData.firstPlayer + "\n");
            fw.write(SettingsData.secondPlayer + "\n");
            fw.write(size);
            System.out.println("Settings have been saved.");
        } catch (IOException e) {
            System.out.println("File already occured.");
        }
    }
}
