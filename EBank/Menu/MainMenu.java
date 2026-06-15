package EBank.Menu;

import java.util.Scanner;
import EBank.Bank;

public class MainMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final String SAVE_FILE = "bank.txt";
    private static final Bank bank = loadBank();

    private static final String MENU = """
            
            ===== EBank SYSTEM =====
            1. Register
            2. Client Login
            3. Employee Login
            0. Exit
            ========================
            """;

    private static Bank loadBank() {
        Bank loaded = Bank.loadFromFile(SAVE_FILE);
        if (loaded != null) {
            System.out.println("Bank data loaded: " + SAVE_FILE);
            return loaded;
        }
        return Bank.getInstance("EBank");
    }

    public static void start() {

        while (true) {
            System.out.println(MENU);
            System.out.print("Choose: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    new RegisterMenu().start();
                    break;
                case 2:
                    new ClientLoginMenu(bank).start();
                    break;
                case 3:
                    new EmployeeLoginMenu(bank).start();
                    break;
                case 0:
                    bank.saveToFile(SAVE_FILE);
                    System.out.println("Exit...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}