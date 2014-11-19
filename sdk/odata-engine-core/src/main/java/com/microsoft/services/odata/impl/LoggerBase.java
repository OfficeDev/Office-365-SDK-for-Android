package com.microsoft.services.odata.impl;

import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Logger;

/**
 * The type Logger impl.
 */
public abstract class LoggerBase implements Logger {
    /**
     * The Log level.
     */
    int logLevel;
    /**
     * The Enabled.
     */
    boolean enabled;

    /**
     * Instantiates a new Logger impl.
     */
    public LoggerBase() {
        this.logLevel = LogLevel.ERROR.getValue();
        this.enabled = true;
    }

    private static final String TAG = "Office365-SDK";

    @Override
    public void log(String content, LogLevel logLevel) {
        if (!this.enabled) {
            return;
        }

        if ((this.logLevel & logLevel.getValue()) != logLevel.getValue()) {
            return;
        }

        if (content != null) {
            print(content, logLevel);
        }
    }

    public abstract void print(String content, LogLevel logLevel);



    /**
     * Sets log level.
     *
     * @param logLevel the log level
     */
    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel.getValue();
    }

    /**
     * Is enabled.
     *
     * @return the boolean
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
