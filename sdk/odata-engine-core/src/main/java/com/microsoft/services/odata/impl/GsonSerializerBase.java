package com.microsoft.services.odata.impl;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.services.odata.Constants;
import com.microsoft.services.odata.interfaces.JsonSerializer;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.microsoft.services.odata.Helpers.getReservedNames;

/**
 * The type Gson serializer.
 */
public abstract class GsonSerializerBase implements JsonSerializer {
    private static Map<String, Class<?>> cachedClassesFromOData = new ConcurrentHashMap<String, Class<?>>();

    private Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .registerTypeAdapter(Calendar.class, new CalendarTypeAdapter())
                .registerTypeAdapter(GregorianCalendar.class, new CalendarTypeAdapter())
                .registerTypeAdapter(byte[].class, getByteArrayTypeAdapter())
                .create();
    }

    protected abstract ByteArrayTypeAdapterBase getByteArrayTypeAdapter();

    @Override
    public String serialize(Object objectToSerialize) {
        Gson serializer = createGson();
        JsonElement json = serializer.toJsonTree(objectToSerialize);
        sanitizePostSerialization(json);

        return json.toString();
    }

    @Override
    public <E> E deserialize(String payload, Class<E> clazz) {
        Gson serializer = createGson();
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(payload);
        sanitizeForDeserialization(json);

        Package pkg = clazz.getPackage();
        Class overridenClass = getClassFromJson(json, pkg);

        if (overridenClass != null) {
            clazz = overridenClass;
        }

        return serializer.fromJson(json, clazz);
    }

    protected Class getClassFromJson(JsonElement json, Package pkg) {
        try {
            if (json.isJsonObject()) {
                JsonObject jsonObject = json.getAsJsonObject();

                if (jsonObject.has(Constants.ODATA_TYPE_PROPERTY_NAME)) {
                    String dataType = jsonObject.get(Constants.ODATA_TYPE_PROPERTY_NAME).getAsString();
                    if (cachedClassesFromOData.containsKey(dataType)) {
                        return cachedClassesFromOData.get(dataType);
                    }

                    String[] parts = dataType.split("\\.");
                    String className = parts[parts.length - 1];

                    String classFullName = pkg.getName() + "." + className;
                    Class<?> derivedClass = Class.forName(classFullName);
                    Class<?> baseClass = Class.forName(pkg.getName() + "." + Constants.ODATA_ENTITY_BASE_CLASS_NAME);

                    Object instance = derivedClass.newInstance();

                    Field field = baseClass.getDeclaredField(Constants.ODATA_TYPE_PROPERTY_NAME);
                    if (field != null) {
                        field.setAccessible(true);
                        String val = (String) field.get(instance);
                        if (val.equals(dataType)) {
                            cachedClassesFromOData.put(dataType, derivedClass);
                            return derivedClass;
                        }
                    }
                }
            }
        } catch (Throwable ignore) {
            // if, for any reason, the sub-class cannot be loaded, just continue and the base class will
            // be used for serialization
        }

        return null;
    }

    @Override
    public <E> List<E> deserializeList(String payload, Class<E> clazz) {
        Gson serializer = createGson();

        JsonParser parser = new JsonParser();
        JsonObject json = (JsonObject) parser.parse(payload);

        JsonElement jsonArray = json.get("value");
        sanitizeForDeserialization(jsonArray);

        Package pkg = clazz.getPackage();
        ArrayList<E> arrayList = new ArrayList<E>();

        for(JsonElement item : jsonArray.getAsJsonArray()) {
            Class currentClass = clazz;
            Class overridenClass = getClassFromJson(item, pkg);

            if (overridenClass != null) {
                currentClass = overridenClass;
            }

            E deserializedItem  = (E) serializer.fromJson(item, currentClass);
            arrayList.add(deserializedItem);
        }

        return arrayList;
    }

    private void sanitizePostSerialization(JsonElement json) {
        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            for (JsonElement subElement : jsonArray) {
                sanitizePostSerialization(subElement);
            }
        } else if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();


            Set<Map.Entry<String, JsonElement>> entries = new HashSet<Map.Entry<String, JsonElement>>(jsonObject.entrySet());

            for (Map.Entry<String, JsonElement> entry : entries) {
                String propertyName = entry.getKey();
                JsonElement subElement = entry.getValue();

                String newName = propertyName;

                if (newName.startsWith(Constants.PROPERTY_NAME_RESERVED_PREFIX)) {
                    newName = newName.substring(Constants.PROPERTY_NAME_RESERVED_PREFIX.length());
                    if (getReservedNames().contains(newName)) {
                        jsonObject.remove(newName);
                        jsonObject.add(propertyName, subElement);
                    }
                } else if (propertyName.equals(Constants.ODATA_TYPE_PROPERTY_NAME)) {
                    jsonObject.remove(Constants.ODATA_TYPE_PROPERTY_NAME);
                    jsonObject.add(Constants.ODATA_TYPE_JSON_PROPERTY, subElement);
                }

                sanitizePostSerialization(subElement);
            }
        }

    }

    private void sanitizeForDeserialization(JsonElement json) {
        if (json.isJsonArray()) {
            JsonArray jsonArray = json.getAsJsonArray();
            for (JsonElement subElement : jsonArray) {
                sanitizeForDeserialization(subElement);
            }
        } else if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();

            Set<Map.Entry<String, JsonElement>> entries = new HashSet<Map.Entry<String, JsonElement>>(jsonObject.entrySet());

            for (Map.Entry<String, JsonElement> entry : entries) {
                String propertyName = entry.getKey();
                JsonElement subElement = entry.getValue();

                String newName = Constants.PROPERTY_NAME_RESERVED_PREFIX + propertyName;
                if (getReservedNames().contains(propertyName)) {
                    jsonObject.remove(propertyName);
                    jsonObject.add(newName, subElement);
                } else {
                    String oDataTypeName = Constants.ODATA_TYPE_PROPERTY_NAME;
                    if (propertyName.equals(Constants.ODATA_TYPE_JSON_PROPERTY)) {
                        jsonObject.remove(propertyName);
                        jsonObject.add(oDataTypeName, subElement);
                    }
                }

                sanitizePostSerialization(subElement);
            }
        }
    }
}
