package appenders;

import formatters.LogFormatter;
import lombok.RequiredArgsConstructor;
import model.LogMessage;

@RequiredArgsConstructor
public class ConsoleAppender implements LogAppender{
    private final LogFormatter formatter;

    @Override
    public void append(LogMessage message) {
        System.out.println(formatter.format(message));
    }
}
