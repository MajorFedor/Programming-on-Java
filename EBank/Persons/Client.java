package EBank.Persons;

import java.util.Objects;

import EBank.BankAccount.Account.BankAccount;
import EBank.BankAccount.Card.CardDetails;

public class Client extends Person {

    private long accountNumber;
    private int creditScore;
    private boolean isActive;
    private CardDetails card;
    private BankAccount bankAccount;
    private static long nextAccountNumber = 30L;

    public Client(String fullName, String passportNumber, String mobileNumber, int age, String dateOfBirth, String residentialAddress, BankAccount ba) {
        super(fullName, passportNumber, mobileNumber, age, dateOfBirth, residentialAddress);
        this.accountNumber = nextAccountNumber++;
        this.creditScore = 5;
        this.isActive = false;
        this.bankAccount = ba;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public int getCreditScore() {
        return this.creditScore;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public CardDetails getCard() {
        return this.card;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public static void setNextAccountNumber(long nextAccountNumber) {
        Client.nextAccountNumber = nextAccountNumber;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        String balanceStr = (bankAccount != null) ? String.valueOf(bankAccount.getBalance()) : "no account";
        return "Client{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balanceStr +
                ", active=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return accountNumber == client.getAccountNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}