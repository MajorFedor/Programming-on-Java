package logFilter.LogFilter;

import logFilter.LogLevel.LogLevel;

public interface logFilter {
    void filter(String source_file, String target_file, LogLevel level);
}
