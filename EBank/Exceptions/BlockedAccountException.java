package EBank.Exceptions;

public class BlockedAccountException extends IllegalStateException {
    public BlockedAccountException(String m) {
        super(m);
    }
}
