package EBank.Menu;

import java.util.Scanner;
import EBank.Bank;
import EBank.Persons.Client;

public class ClientLoginMenu {

    private final Scanner sc = new Scanner(System.in);
    private final Bank bank;

    public ClientLoginMenu(Bank bank) {
        this.bank = bank;
    }

    public void start() {
        System.out.println("\n--- Client Login ---");
        System.out.print("Enter your account number: ");

        if (!sc.hasNextLong()) {
            System.out.println("Invalid input.");
            sc.nextLine();
            return;
        }

        long acc = sc.nextLong();
        sc.nextLine();

        Client client = null;
        for (Client c : bank.getClients()) {
            if (c.getAccountNumber() == acc) {
                client = c;
                break;
            }
        }

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("Welcome, " + client.getName() + "!");
        new ClientMenu(bank, client).start();
    }
}