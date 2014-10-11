package com.microsoft.office365.odata.impl;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * The type Calendar type adapter.
 */
public class CalendarTypeAdapter implements com.google.gson.JsonSerializer<Calendar>, com.google.gson.JsonDeserializer<Calendar> {

    @Override
    public Calendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String strVal = json.getAsString();

        try {
            return deserialize(strVal);
        }
        catch (ParseException e) {
            throw new JsonParseException(e);
        }
    }

    /**
     * Deserializes an ISO-8601 formatted date
     * @param strVal the str val
     * @return the calendar
     * @throws ParseException the parse exception
     */
    public static Calendar deserialize(String strVal) throws ParseException {
        // Change Z to +00:00 to adapt the string to a format
        // that can be parsed in Java
        String s = strVal.replace("Z", "+00:00");

        // Parse the well-formatted date string
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getDefault());

        Date date = dateFormat.parse(s);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        String formatted = serialize(src);

        return new JsonPrimitive(formatted);
    }

    /**
     * Serialize string.
     *
     * @param src the src
     * @return the string
     */
    public static String serialize(Calendar src) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(src.getTime());
    }

}
