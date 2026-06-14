package EBank.Menu;

import java.util.Scanner;

import EBank.Bank;
import EBank.BankAccount.Account.BankAccount;
import EBank.BankAccount.Account.DepositAccount;
import EBank.BankAccount.Account.Transaction.Transaction;
import EBank.Memento.BankAccountHistory;
import EBank.Observer.BankEvent;
import EBank.Persons.Client;

public class ClientMenu {

    private final Scanner sc = new Scanner(System.in);
    private final Bank bank;
    private final Client client;

    private static final String MENU = """
            
            ===== CLIENT MENU =====
            1. View account info
            2. Deposit
            3. Withdraw
            4. Transfer to another account
            5. Transaction history
            6. Apply interest (deposit only)
            7. Undo last operation
            0. Logout
            =======================
            """;

    public ClientMenu(Bank bank, Client client) {
        this.bank = bank;
        this.client = client;
    }

    public void start() {
        BankAccount account = client.getBankAccount();

        if (account == null) {
            System.out.println("No bank account found. Please contact an employee.");
            return;
        }

        BankAccountHistory history = new BankAccountHistory(account);

        while (true) {
            System.out.println(MENU);
            System.out.print("Choose option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid choice.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Account Info ---");
                    System.out.println(account);
                    break;

                case 2:
                    System.out.print("Amount to deposit: ");
                    if (!sc.hasNextDouble()) { sc.nextLine(); break; }
                    double depositAmount = sc.nextDouble();
                    sc.nextLine();
                    try {
                        history.backup();
                        account.deposit(depositAmount);
                        Transaction depositTx = new Transaction(
                            String.valueOf(account.getId()), "self", depositAmount, "Deposit"
                        );
                        depositTx.complete();
                        account.addTransaction(depositTx);
                        System.out.println("Deposited: " + depositAmount);
                        System.out.println("New balance: " + account.getBalance());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Amount to withdraw: ");
                    if (!sc.hasNextDouble()) { sc.nextLine(); break; }
                    double withdrawAmount = sc.nextDouble();
                    sc.nextLine();
                    try {
                        history.backup();
                        account.withdraw(withdrawAmount);
                        Transaction withdrawTx = new Transaction(
                            String.valueOf(account.getId()), "self", withdrawAmount, "Withdrawal"
                        );
                        withdrawTx.complete();
                        account.addTransaction(withdrawTx);
                        System.out.println("Withdrawn: " + withdrawAmount);
                        System.out.println("New balance: " + account.getBalance());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    transfer(account, history);
                    break;

                case 5:
                    System.out.println("--- Transaction History ---");
                    if (account.getTransactions().isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (Transaction t : account.getTransactions()) {
                            System.out.println(t);
                        }
                    }
                    break;

                case 6:
                    if (account instanceof DepositAccount) {
                        DepositAccount da = (DepositAccount) account;
                        history.backup();
                        da.applyInterest();
                        System.out.println("Interest applied. New balance: " + da.getBalance());
                    } else {
                        System.out.println("This option is only available for deposit accounts.");
                    }
                    break;

                case 7:
                    if (history.hasHistory()) {
                        history.undo();
                        System.out.println("Last operation undone.");
                        System.out.println("Current balance: " + account.getBalance());
                    } else {
                        System.out.println("Nothing to undo.");
                    }
                    break;

                case 0:
                    System.out.println("Logged out.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void transfer(BankAccount fromAccount, BankAccountHistory history) {
        System.out.print("Enter recipient account ID: ");
        if (!sc.hasNextLong()) { sc.nextLine(); return; }
        long toId = sc.nextLong();
        sc.nextLine();

        BankAccount toAccount = bank.getBankAccounts().get(toId);
        if (toAccount == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        if (toAccount.getId() == fromAccount.getId()) {
            System.out.println("Cannot transfer to your own account.");
            return;
        }

        System.out.print("Amount: ");
        if (!sc.hasNextDouble()) { sc.nextLine(); return; }
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Description (Enter to skip): ");
        String desc = sc.nextLine().trim();
        if (desc.isBlank()) {
            desc = "Transfer";
        }

        Transaction t = new Transaction(
            String.valueOf(fromAccount.getId()),
            String.valueOf(toAccount.getId()),
            amount, desc
        );

        try {
            history.backup();
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            fromAccount.events.notify(BankEvent.TRANSFER, "To account #" + toId + " | Amount: " + amount);
            t.complete();
            fromAccount.addTransaction(t);
            toAccount.addTransaction(t);
            System.out.println("Transferred: " + amount + " to account #" + toId);
        } catch (Exception e) {
            t.cancel();
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }
}