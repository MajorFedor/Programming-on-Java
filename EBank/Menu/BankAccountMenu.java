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

public class BankAccountMenu {

    private final Scanner sc = new Scanner(System.in);
    private final Bank bank;

    public BankAccountMenu(Bank bank) {
        this.bank = bank;
    }

    public void start(Client client) {
        while (true) {
            System.out.println("\n=== Account Menu ===");
            System.out.println("1. Open deposit account");
            System.out.println("2. Open credit account");
            System.out.println("3. Open card account");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Initial balance: ");
                    double depositBalance = sc.nextDouble();
                    System.out.print("Interest rate: ");
                    double interestRate = sc.nextDouble();
                    BankAccount deposit = new DepositAccount(depositBalance, interestRate);
                    client.setBankAccount(deposit);
                    bank.addBankAccounts(deposit);
                    System.out.println("Deposit account opened");
                    break;
                case 2:
                    System.out.print("Initial balance: ");
                    double creditBalance = sc.nextDouble();
                    System.out.print("Interest rate: ");
                    double creditRate = sc.nextDouble();
                    System.out.print("Credit limit: ");
                    double creditLimit = sc.nextDouble();
                    BankAccount credit = new CreditAccount(creditBalance, creditRate, creditLimit);
                    client.setBankAccount(credit);
                    bank.addBankAccounts(credit);
                    System.out.println("Credit account opened");
                    break;
                case 3:
                    System.out.print("Initial balance: ");
                    double cardBalance = sc.nextDouble();
                    System.out.println("Card category (STANDART/GOLD/PLATINUM): ");
                    sc.nextLine();
                    String category = sc.nextLine().trim().toUpperCase();
                    System.out.print("Card expiry (MM/YY): ");
                    String expiry = sc.nextLine().trim();
                    CardDetails card = new CardFactory().createCard(CardCategory.valueOf(category), expiry);
                    BankAccount cardAccount = new CardAccount(cardBalance, card);
                    client.setBankAccount(cardAccount);
                    bank.addBankAccounts(cardAccount);
                    System.out.println("Card account opened");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}