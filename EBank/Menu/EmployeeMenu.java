package EBank.Menu;

import java.util.Scanner;

import EBank.Bank;
import EBank.BankAccount.Account.BankAccount;
import EBank.Persons.Client;
import EBank.Persons.Employee;

public class EmployeeMenu {

    private final Scanner sc = new Scanner(System.in);
    private final Bank bank;
    private final Employee employee;

    private static final String MENU = """
            
            ===== EMPLOYEE MENU =====
            1. View all clients
            2. View all accounts
            3. Block account
            4. Unblock account
            5. View client details
            6. View statistics
            7. View event logs
            0. Logout
            =========================
            """;

    public EmployeeMenu(Bank bank, Employee employee) {
        this.bank = bank;
        this.employee = employee;
    }

    public void start() {
        while (true) {
            System.out.println(MENU);
            System.out.print("Choose option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    viewClients();
                    break;
                case 2:
                    viewAccounts();
                    break;
                case 3:
                    blockAccount();
                    break;
                case 4:
                    unblockAccount();
                    break;
                case 5:
                    viewClientDetails();
                    break;
                case 6:
                    System.out.println("\n--- Statistics ---");
                    bank.getStatistic().printStatistics();
                    break;
                case 7:
                    System.out.println("\n--- Event Logs ---");
                    bank.getStatistic().printLogs();
                    break;
                case 0:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void viewClients() {
        System.out.println("\n--- Clients (" + bank.getClients().size() + ") ---");
        if (bank.getClients().isEmpty()) {
            System.out.println("No clients registered.");
            return;
        }
        for (Client c : bank.getClients()) {
            System.out.println(c);
        }
    }

    private void viewAccounts() {
        System.out.println("\n--- Bank Accounts (" + bank.getBankAccounts().size() + ") ---");
        if (bank.getBankAccounts().isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (BankAccount a : bank.getBankAccounts().values()) {
            System.out.println(a);
        }
    }

    private void blockAccount() {
        System.out.print("Enter account ID to block: ");
        if (!sc.hasNextLong()) { sc.nextLine(); return; }
        long id = sc.nextLong();
        sc.nextLine();

        BankAccount account = bank.getBankAccounts().get(id);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        if (account.isBlocked()) {
            System.out.println("Account is already blocked.");
            return;
        }

        employee.blockAccount(account);
        System.out.println("Account #" + id + " blocked.");
    }

    private void unblockAccount() {
        System.out.print("Enter account ID to unblock: ");
        if (!sc.hasNextLong()) { sc.nextLine(); return; }
        long id = sc.nextLong();
        sc.nextLine();

        BankAccount account = bank.getBankAccounts().get(id);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        if (!account.isBlocked()) {
            System.out.println("Account is not blocked.");
            return;
        }

        employee.unblockAccount(account);
        System.out.println("Account #" + id + " unblocked.");
    }

    private void viewClientDetails() {
        System.out.print("Enter client account number: ");
        if (!sc.hasNextLong()) { sc.nextLine(); return; }
        long accNum = sc.nextLong();
        sc.nextLine();

        Client client = null;
        for (Client c : bank.getClients()) {
            if (c.getAccountNumber() == accNum) {
                client = c;
                break;
            }
        }

        if (client == null) {
            System.out.println("Client not found.");
            return;
        }

        System.out.println("\n--- Client Details ---");
        System.out.println("Name: " + client.getName());
        System.out.println("Account number: " + client.getAccountNumber());
        System.out.println("Mobile: " + client.getMobileNumber());
        System.out.println("Age: " + client.getAge());
        System.out.println("Address: " + client.getResidentialAddress());
        System.out.println("Credit score: " + client.getCreditScore());

        BankAccount acc = client.getBankAccount();
        if (acc != null) {
            System.out.println("Account:  " + acc);
            System.out.println("Transactions: " + acc.getTransactions().size());
        } else {
            System.out.println("Account: none");
        }
    }
}