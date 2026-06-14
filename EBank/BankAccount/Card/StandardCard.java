package EBank.BankAccount.Card;

public class StandardCard extends CardDetails {
    public StandardCard(String cardExpiry) {
        super(cardExpiry, CardCategory.STANDART, 10000, 1.5);
    }
}