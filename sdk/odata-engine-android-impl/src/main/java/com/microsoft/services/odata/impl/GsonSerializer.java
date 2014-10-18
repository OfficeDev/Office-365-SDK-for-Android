package com.microsoft.services.odata.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.services.odata.interfaces.JsonSerializer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * The type Gson serializer.
 */
public class GsonSerializer extends GsonSerializerBase {

    @Override
    protected ByteArrayTypeAdapterBase getByteArrayTypeAdapter() {
        return new ByteArrayTypeAdapterImpl();
    }
}
