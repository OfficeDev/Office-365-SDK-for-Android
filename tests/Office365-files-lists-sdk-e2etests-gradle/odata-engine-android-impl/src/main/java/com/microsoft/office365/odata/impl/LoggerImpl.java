package com.microsoft.office365.odata.impl;

import android.util.Log;

import com.microsoft.office365.odata.interfaces.LogLevel;
import com.microsoft.office365.odata.interfaces.Logger;

public class LoggerImpl implements Logger {
    @Override
    public void log(String content, LogLevel logLevel) {
        if (content != null) {
            Log.e("SDK", content);
        }
    }
}
