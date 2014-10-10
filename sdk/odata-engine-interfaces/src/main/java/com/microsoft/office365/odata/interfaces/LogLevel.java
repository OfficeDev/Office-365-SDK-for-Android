package com.microsoft.office365.odata.interfaces;

public enum LogLevel {
    VERBOSE (15),
    INFO (7),
    WARNING (3),
    ERROR (1);

    private final int value;
    private LogLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
