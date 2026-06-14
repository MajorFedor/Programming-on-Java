package EBank.Exceptions;

public class InvalidAmountException extends IllegalArgumentException {
    public InvalidAmountException(String m) {
        super(m);
    }
}