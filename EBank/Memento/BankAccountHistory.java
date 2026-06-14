package EBank.Memento;

import java.util.ArrayList;
import java.util.List;

import EBank.BankAccount.Account.BankAccount;

public class BankAccountHistory {

    private final List<BankAccountMemento> history;
    private final BankAccount account;

    public BankAccountHistory(BankAccount account) {
        this.history = new ArrayList<>();
        this.account = account;
    }

    public void backup() {
        history.add(account.save());
    }

    public void undo() {
        if (history.isEmpty()) {
            throw new IllegalStateException("No saved states");
        }
        account.restore(history.remove(history.size() - 1));
    }

    public boolean hasHistory() {
        return !history.isEmpty();
    }
}