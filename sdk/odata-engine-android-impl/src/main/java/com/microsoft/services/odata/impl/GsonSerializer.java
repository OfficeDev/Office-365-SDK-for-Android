package com.microsoft.services.odata.impl;


import com.google.gson.JsonParser;

import java.util.Map;

/**
 * The type Gson serializer.
 */
public class GsonSerializer extends GsonSerializerBase {

    @Override
    protected ByteArrayTypeAdapterBase getByteArrayTypeAdapter() {
        return new ByteArrayTypeAdapterImpl();
    }
}
