package com.microsoft.office365.odata.impl;

import android.util.Log;

import com.microsoft.office365.odata.interfaces.LogLevel;
import com.microsoft.office365.odata.interfaces.Logger;

public class LoggerImpl implements Logger {
    boolean verboseMode = false;
    boolean enabled = true;

    private static final String TAG = "Office365-SDK";

    @Override
    public void log(String content, LogLevel logLevel) {
        if (!this.enabled) {
            return;
        }

        if (!this.verboseMode && (logLevel == LogLevel.VERBOSE || logLevel == LogLevel.WARNING)) {
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

    public void setVerboseMode(boolean verbose) {
        this.verboseMode = verbose;
    }

    public boolean getVerboseMode() {
        return this.verboseMode;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
