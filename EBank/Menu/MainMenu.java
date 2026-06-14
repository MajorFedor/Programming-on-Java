package EBank.Menu;

import java.util.Scanner;
import EBank.Bank;

public class MainMenu {

    private static final Scanner sc = new Scanner(System.in);
    private static final Bank bank = Bank.getInstance("EBank");

    private static final String MENU = """
            
            ===== EBank SYSTEM =====
            1. Register
            2. Client Login
            3. Employee Login
            0. Exit
            ========================
            """;

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
                    bank.saveToFile("bank.dat");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}