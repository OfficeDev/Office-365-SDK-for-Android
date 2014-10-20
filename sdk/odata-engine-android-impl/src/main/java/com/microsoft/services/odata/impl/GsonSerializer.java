package com.microsoft.services.odata.impl;


/**
 * The type Gson serializer.
 */
public class GsonSerializer extends GsonSerializerBase {

    @Override
    protected ByteArrayTypeAdapterBase getByteArrayTypeAdapter() {
        return new ByteArrayTypeAdapterImpl();
    }
}
