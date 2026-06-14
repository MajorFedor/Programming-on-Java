package EBank.Exceptions;

public class InvalidSalaryException extends IllegalArgumentException {
    public InvalidSalaryException(String m){
        super(m);
    }
}
