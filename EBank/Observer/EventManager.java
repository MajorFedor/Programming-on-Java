package EBank.Observer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EventManager implements Serializable{
    Map<BankEvent, List<EventListener>> listeners = new HashMap<>();

    public EventManager() {
        for (BankEvent event : BankEvent.values()) {
            listeners.put(event, new ArrayList<>());
        }
    }

    public void subscribe(BankEvent eventType, EventListener listener){
        List<EventListener> users = listeners.get(eventType);
        users.add(listener);
    }

    public void unsubscribe(BankEvent eventType, EventListener listener){
        List<EventListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void clearListeners(BankEvent eventType){
        List<EventListener> users = listeners.get(eventType);
        users.clear();
    }

    public void notify(BankEvent eventType, String info) {
        List<EventListener> users = listeners.get(eventType);
        for (EventListener listener : users) {
            listener.onEvent(eventType, info, LocalDateTime.now());
        }
    }
}
