package com.microsoft.office365.odata.impl;


import com.microsoft.office365.odata.interfaces.LogLevel;
import com.microsoft.office365.odata.interfaces.Logger;

public class LoggerImpl implements Logger {
    @Override
    public void log(String content, LogLevel logLevel) {
        if (content != null) {
            System.out.println(content);
        }
    }
}
