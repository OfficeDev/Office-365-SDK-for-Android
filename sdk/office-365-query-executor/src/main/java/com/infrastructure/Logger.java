package com.infrastructure;

/**
 * Created by marcote on 9/25/14.
 */
public class Logger {
    static StringBuilder mLog = new StringBuilder();

    public synchronized static void log(String message, LogLevel verbose) {
        log(message);
    }
    public synchronized static void log(Object o){
        if (o != null) {
            System.out.println(o.toString());
        }
    }
}
