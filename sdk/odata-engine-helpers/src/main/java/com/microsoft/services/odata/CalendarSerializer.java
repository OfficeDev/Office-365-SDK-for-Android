package com.microsoft.services.odata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarSerializer {
    /**
     * Deserializes an ISO-8601 formatted date
     * @param strVal the str val
     * @return the calendar
     * @throws java.text.ParseException the parse exception
     */
    public static Calendar deserialize(String strVal) throws ParseException {
        // Change Z to +0000 to adapt the string to a format
        // that can be parsed in Java
        boolean hasZ = strVal.indexOf('Z') != -1;
        String s = (hasZ) ? strVal.replace("Z", "+0000") : strVal;
        String zsuffix = (hasZ) ? "Z" : "";

        // Parse the well-formatted date string
        String datePattern;
        if(s.contains(".")){
            datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS" + zsuffix;
        } else {
            datePattern = "yyyy-MM-dd'T'HH:mm:ss" + zsuffix;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        dateFormat.setTimeZone(TimeZone.getDefault());

        Date date = dateFormat.parse(s);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Serialize string.
     *
     * @param src the src
     * @return the string
     */
    public static String serialize(Calendar src) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSSSSS'Z'", Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(src.getTime());
    }
}
