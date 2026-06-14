package EBank.Exceptions;

public class CreditlimitExceedException extends IllegalStateException {
    public CreditlimitExceedException (String m){
        super(m);
    }

}
