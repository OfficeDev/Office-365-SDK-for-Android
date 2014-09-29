package com.impl;

import com.interfaces.LogLevel;
import com.interfaces.Logger;

public class LoggerImpl implements Logger {
    @Override
    public void log(String content, LogLevel logLevel) {
        if (content != null) {
            System.out.println(content);
        }
    }
}
