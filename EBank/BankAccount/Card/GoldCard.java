package EBank.BankAccount.Card;

public class GoldCard extends CardDetails {
    public GoldCard(String cardExpiry) {
        super(cardExpiry, CardCategory.GOLD, 25000, 2);
    }
}