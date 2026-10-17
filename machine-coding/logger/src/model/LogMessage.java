package model;

import enums.LogLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LogMessage {
    private LogLevel level;
    private String message;
    private long timestamp;
}