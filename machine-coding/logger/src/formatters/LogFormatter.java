package formatters;

import model.LogMessage;

public interface LogFormatter {
    String format(LogMessage message);
}
