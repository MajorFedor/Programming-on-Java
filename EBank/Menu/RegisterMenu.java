package EBank.Menu;

import java.util.Scanner;

import EBank.Bank;
import EBank.BankAccount.Account.BankAccount;
import EBank.BankAccount.Account.CardAccount;
import EBank.BankAccount.Account.CreditAccount;
import EBank.BankAccount.Account.DepositAccount;
import EBank.BankAccount.Card.CardCategory;
import EBank.BankAccount.Card.CardDetails;
import EBank.BankAccount.Card.CardFactory;
import EBank.Persons.Client;
import EBank.Persons.Employee;

public class RegisterMenu {

    private final Scanner sc = new Scanner(System.in);
    private final Bank bank = Bank.getInstance("EBank");

    private static final String MENU = """
            
            ===== REGISTER MENU =====
            1. Register Client
            2. Register Employee
            0. Back
            =========================
            """;

    public void start() {
        while (true) {
            System.out.println(MENU);
            System.out.println("Choose option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    registerClient();
                    break;
                case 2:
                    registerEmployee();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void registerClient() {
        System.out.println("\n--- Client Registration ---");

        System.out.print("Full name: ");
        String fullName = sc.nextLine().trim();

        System.out.print("Passport number: ");
        String passport = sc.nextLine().trim();

        System.out.print("Mobile number: ");
        while (!sc.hasNextInt()) {
            System.out.println("Enter digits: ");
            sc.nextLine();
        }
        String mobile = sc.nextLine().trim();

        System.out.println("Age: ");
        while (!sc.hasNextInt()) {
            System.out.println("Enter number: ");
            sc.nextLine();
        }
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println("Date of birth (DD.MM.YYYY): ");
        String dob = sc.nextLine().trim();

        System.out.println("Address: ");
        String address = sc.nextLine().trim();

        Client client = new Client(fullName, passport, mobile, age, dob, address, null);
        bank.addClient(client);

        System.out.println("\nNow let's open a bank account for the client.");
        openAccountForClient(client);

        System.out.println("\nClient successfully registered.");
        System.out.println("Account number (login): " + client.getAccountNumber());
    }

    private void openAccountForClient(Client client) {
        while (true) {
            System.out.println("""
                    
                    Choose account type:
                    1. Deposit account
                    2. Credit account
                    3. Card account
                    """);
            System.out.print("Choose: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                continue;
            }
            int choice = sc.nextInt();
            sc.nextLine();

            BankAccount account = null;

            switch (choice) {
                case 1:
                    System.out.println("Initial balance: ");
                    while (!sc.hasNextDouble()) { sc.nextLine(); }
                    double depositBalance = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Interest rate (%): ");
                    while (!sc.hasNextDouble()) { sc.nextLine(); }
                    double depositRate = sc.nextDouble();
                    sc.nextLine();

                    account = new DepositAccount(depositBalance, depositRate);
                    System.out.println("Deposit account created.");
                    break;

                case 2:
                    System.out.println("Initial balance: ");
                    while (!sc.hasNextDouble()) { sc.nextLine(); }
                    double creditBalance = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Interest rate (%): ");
                    while (!sc.hasNextDouble()) { sc.nextLine(); }
                    double creditRate = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Credit limit: ");
                    while (!sc.hasNextDouble()) { sc.nextLine(); }
                    double creditLimit = sc.nextDouble();
                    sc.nextLine();

                    account = new CreditAccount(creditBalance, creditRate, creditLimit);
                    System.out.println("Credit account created.");
                    break;

                case 3:
                    System.out.println("Initial balance: ");
                    while (!sc.hasNextDouble()) { sc.nextLine(); }
                    double cardBalance = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Card category (STANDART / GOLD / PLATINUM): ");
                    String categoryStr = sc.nextLine().trim().toUpperCase();
                    CardCategory cardCategory;
                    try {
                        cardCategory = CardCategory.valueOf(categoryStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category, defaulting to STANDART.");
                        cardCategory = CardCategory.STANDART;
                    }

                    System.out.println("Card expiry (MM/YY): ");
                    String expiry = sc.nextLine().trim();

                    CardDetails card = new CardFactory().createCard(cardCategory, expiry);
                    account = new CardAccount(cardBalance, card);
                    System.out.println("Card account created. Card number: " + card.getCardNumber());
                    break;

                default:
                    System.out.println("Invalid choice.");
                    continue;
            }

            client.setBankAccount(account);
            bank.addBankAccounts(account);
            break;
        }
    }

    private void registerEmployee() {
        System.out.println("\n--- Employee Registration ---");

        System.out.println("Full name: ");
        String fullName = sc.nextLine().trim();

        System.out.println("Passport number: ");
        String passport = sc.nextLine().trim();

        System.out.println("Mobile number: ");
        String mobile = sc.nextLine().trim();

        System.out.println("Age: ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter number: ");
            sc.nextLine();
        }
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println("Date of birth (DD.MM.YYYY): ");
        String dob = sc.nextLine().trim();

        System.out.println("Address: ");
        String address = sc.nextLine().trim();

        System.out.println("Position: ");
        String position = sc.nextLine().trim();

        System.out.println("Salary: ");
        while (!sc.hasNextInt()) {
            System.out.println("Enter number: ");
            sc.nextLine();
        }
        int salary = sc.nextInt();
        sc.nextLine();

        Employee employee = new Employee(fullName, passport, mobile, age, dob, address, position, salary);
        bank.addEmployee(employee);

        System.out.println("\nEmployee successfully registered.");
        System.out.println("Employee ID (login): " + employee.getEmployeeId());
    }
}