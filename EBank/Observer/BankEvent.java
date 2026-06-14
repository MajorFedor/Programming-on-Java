package EBank.Observer;

public enum BankEvent {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdrawal"),
    BLOCK("Account blocked"),
    UNBLOCK("Account unblocked"),
    TRANSFER("Money transfer");

    private final String description;

    BankEvent(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
