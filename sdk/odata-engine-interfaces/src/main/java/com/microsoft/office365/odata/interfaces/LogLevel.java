package com.microsoft.office365.odata.interfaces;

/**
 * The enum Log level.
 */
public enum LogLevel {
    /**
     * The VERBOSE.
     */
    VERBOSE (15),
    /**
     * The INFO.
     */
    INFO (7),
    /**
     * The WARNING.
     */
    WARNING (3),
    /**
     * The ERROR.
     */
    ERROR (1);

    private final int value;
    private LogLevel(int value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.value;
    }
}
