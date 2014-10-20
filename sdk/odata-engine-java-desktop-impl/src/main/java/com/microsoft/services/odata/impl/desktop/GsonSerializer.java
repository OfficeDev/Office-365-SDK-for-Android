package com.microsoft.services.odata.impl.desktop;

import com.microsoft.services.odata.impl.ByteArrayTypeAdapterBase;
import com.microsoft.services.odata.impl.GsonSerializerBase;

public class GsonSerializer extends GsonSerializerBase {
    @Override
    protected ByteArrayTypeAdapterBase getByteArrayTypeAdapter() {
        return new ByteArrayTypeAdapterImpl();
    }
}
