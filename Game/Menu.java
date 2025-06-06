package Game;

import Settings.*;
import Statistic.*;
import java.util.Scanner;

public class Menu {

    public static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        String menu = """
                --Menu--
                1. Start game.
                2. Settings.
                3. Show statistic.
                4. Exit.
                """;

        while (true) {
            System.out.println(menu);

            System.out.println("Next step: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        startGameMenu();
                        break;
                    case 2:
                        Settings.settingsMenu();
                        break;
                    case 3:
                        ShowStatistic.showStats();
                        break;
                    case 4:
                        System.out.println("Exit...");
                        return;
                    default:
                        break;
                }
            } else {
                System.out.println("Try again.");
                sc.next();
            }
        }
    }

    public static void startGameMenu() {
        String startGameMenu = """
                --Game--
                1.Start game.
                2.Back to menu.
                """;

        while (true) {
            System.out.println(startGameMenu);

            System.out.println("Next step: ");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    Game.startGame(SettingsData.boardSize, true);
                } else if (choice == 2) {
                    break;
                }
            }
        }
    }
}
