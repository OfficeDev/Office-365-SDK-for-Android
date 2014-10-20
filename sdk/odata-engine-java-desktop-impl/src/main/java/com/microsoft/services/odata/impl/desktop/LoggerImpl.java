package com.microsoft.services.odata.impl.desktop;

import com.microsoft.services.odata.impl.LoggerBase;
import com.microsoft.services.odata.interfaces.LogLevel;
import com.microsoft.services.odata.interfaces.Logger;

/**
 * The type Logger impl.
 */
public class LoggerImpl extends LoggerBase{

    @Override
    public void print(String content, LogLevel logLevel) {
        if (content != null) {
            switch (logLevel) {
                case ERROR:
                    System.err.print(content);
                    break;
                case INFO:
                    System.out.print(content);
                    break;
                case VERBOSE:
                    System.out.print(content);
                    break;
                case WARNING:
                    System.err.print(content);
                    break;
            }
        }
    }
}
