package com.microsoft.services.odata.impl;

import android.util.Log;

import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Logger;

/**
 * The type Logger impl.
 */
public class LoggerImpl implements Logger {
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
    public LoggerImpl() {
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
            switch (logLevel) {
                case ERROR:
                    Log.e(TAG, content);
                    break;
                case INFO:
                    Log.i(TAG, content);
                    break;
                case VERBOSE:
                    Log.v(TAG, content);
                    break;
                case WARNING:
                    Log.w(TAG, content);
                    break;
            }
        }
    }

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
