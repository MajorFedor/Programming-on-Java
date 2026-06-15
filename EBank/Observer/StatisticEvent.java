package EBank.Observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticEvent implements EventListener{
    private final Map<BankEvent, Integer> statistic = new HashMap<>();
    private final List<String> logs = new ArrayList<>();
    private transient DateTimeFormatter formatter;

    private DateTimeFormatter getFormatter() {
        if (formatter == null) {
            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        }
        return formatter;
    }

    @Override
    public void onEvent(BankEvent event, String addInfo, LocalDateTime ts) {
        if(statistic.containsKey(event)){
            statistic.put(event, statistic.get(event) + 1);
        } else {
            statistic.put(event, 1);
        }
        logs.add(ts.format(getFormatter()) + " | " + event.getDescription() + " | " + addInfo);
    }

    public void printStatistics() {
        for (BankEvent event : statistic.keySet()) {
            System.out.println(event.getDescription() + ": " + statistic.get(event));
        }
    }

    public void printLogs() {
        for (String log : logs) {
            System.out.println(log);
        }
    }
}