package EBank.BankAccount.Card;

public class PlatinumCard extends CardDetails {
    public PlatinumCard(String cardExpiry) {
        super(cardExpiry, CardCategory.PLATINUM, 35000, 2.25);
    }
}