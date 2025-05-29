package Settings;

import java.util.Scanner;

public class Settings {

    public static Scanner sc = new Scanner(System.in);

    public static void settingsMenu() {

        String settingsMenu = """
                --Settings--
                1. Change size.
                2. Change player name.
                3. Save settings.
                4. Load settings.
                5. Back to menu.
                """;

        while (true) {
            System.out.println(settingsMenu);

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        settingsSizeMenu();
                        break;
                    case 2:
                        changeName();
                        break;
                    case 3:
                        SaveSettings.saveSettings();
                        break;
                    case 4:
                        LoadSettings.loadSettings();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Choose correct menu item. ");
                }
                
            }
        }
    }

    public static int settingsSizeMenu() {
        String settingsSizeMenu = """
                --Board Sizes--
                1. 3x3.
                2. 5x5.
                3. 7x7.
                4. 9x9.
                5. Back to settings
                """;

        while (true) {
            System.out.println(settingsSizeMenu);

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        SettingsData.boardSize = 3;
                        break;
                    case 2:
                        SettingsData.boardSize = 5;
                        break;
                    case 3:
                        SettingsData.boardSize = 7;
                        break;
                    case 4:
                        SettingsData.boardSize = 9;
                        break;
                    default:
                        SettingsData.boardSize = 3;
                        System.out.println("");
                        break;
                }
                break;
            } else {
                System.out.println("Please enter a number.");
                sc.nextLine();
            }
        }
        return SettingsData.boardSize;
    }

    public static void changeName() {

        String menu = """
            First player name: %s
            Second player name: %s
            Do you want to change it?
            Type "yes" if u want, and "no" to return in menu
            """;
        System.out.printf(menu, SettingsData.firstPlayer, SettingsData.secondPlayer);

        String choice = sc.nextLine();
        if(choice.equals("Yes")){
            System.out.println("First player name is:");
            String firstChange = sc.nextLine();
            SettingsData.firstPlayer = firstChange;

            System.out.println("Second player name is:");
            String secondChange = sc.nextLine();
            SettingsData.secondPlayer = secondChange;
        } else {
            return;
        }
    }
}
