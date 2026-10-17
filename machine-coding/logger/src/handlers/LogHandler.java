package handlers;

import appenders.LogAppender;
import enums.LogLevel;
import lombok.Setter;
import model.LogMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {
    @Setter
    protected LogHandler next;
    protected final List<LogAppender> appenders = new CopyOnWriteArrayList<>();//so that it doesnt get overwritten

    public void subscribe(LogAppender observer) {
        appenders.add(observer);
    }

    public void notifyObservers(LogMessage message) {
        for (LogAppender appender : appenders) {
            appender.append(message);
        }
    }

    public void handle(LogMessage message) {
        if (canHandle(message.getLevel())) {
            notifyObservers(message);
        }
        else if (next != null) {
            next.handle(message);
        }
    }

    protected abstract boolean canHandle(LogLevel level);
}

// [console, file]

// removes the file
