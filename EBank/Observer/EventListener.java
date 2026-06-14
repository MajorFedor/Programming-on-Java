package EBank.Observer;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface EventListener extends Serializable {
    void onEvent(BankEvent event, String addInfo, LocalDateTime ldt);
}