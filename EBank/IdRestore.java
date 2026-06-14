package EBank;

import EBank.BankAccount.Account.BankAccount;
import EBank.BankAccount.Account.CardAccount;
import EBank.BankAccount.Account.Transaction.Transaction;
import EBank.BankAccount.Card.CardDetails;
import EBank.Persons.Client;
import EBank.Persons.Employee;
import EBank.Persons.Person;

public final class IdRestore {

    private IdRestore() {
    }

    public static void restore(Bank bank) {
        long maxPersonId = Person.getMaxId();
        long maxAccountNumber = 29L;
        long maxEmployeeId = 0L;
        long maxBankAccountId = 0L;
        long maxTransactionId = 0L;
        long maxCardNumber = CardDetails.getNextCardNumber() - 1;

        for (Client c : bank.getClients()) {
            if (c.getId() > maxPersonId) maxPersonId = c.getId();
            if (c.getAccountNumber() > maxAccountNumber) maxAccountNumber = c.getAccountNumber();
        }

        for (Employee e : bank.getEmployees()) {
            if (e.getId() > maxPersonId) maxPersonId = e.getId();
            if (e.getEmployeeId() > maxEmployeeId) maxEmployeeId = e.getEmployeeId();
        }

        for (BankAccount ba : bank.getBankAccounts().values()) {
            if (ba.getId() > maxBankAccountId) maxBankAccountId = ba.getId();

            for (Transaction t : ba.getTransactions()) {
                if (t.getId() > maxTransactionId) maxTransactionId = t.getId();
            }

            if (ba instanceof CardAccount) {
                CardAccount ca = (CardAccount) ba;
                CardDetails card = ca.getCard();
                if (card != null) {
                    long cardNumber = Long.parseLong(card.getCardNumber());
                    if (cardNumber > maxCardNumber) maxCardNumber = cardNumber;
                }
            }
        }

        Person.setMaxId(maxPersonId);
        Client.setNextAccountNumber(maxAccountNumber + 1);
        Employee.setNextEmployeeId(maxEmployeeId + 1);
        BankAccount.setNextId(maxBankAccountId + 1);
        Transaction.setNextId(maxTransactionId + 1);
        CardDetails.setNextCardNumber(maxCardNumber + 1);
    }
}