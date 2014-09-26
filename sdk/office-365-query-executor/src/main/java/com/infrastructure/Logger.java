package com.infrastructure;

/**
 * Created by marcote on 9/25/14.
 */
public class Logger {
    static StringBuilder mLog = new StringBuilder();

    public synchronized static void Log(String message) {
        if (message != null) {
            mLog.append(message);
            mLog.append("\n");
        }
    }
}
