package com.microsoft.services.odata.impl.desktop;

import com.microsoft.services.odata.interfaces.Base64Encoder;
import com.microsoft.services.odata.impl.ByteArrayTypeAdapterBase;

public class ByteArrayTypeAdapterImpl extends ByteArrayTypeAdapterBase {
    @Override
    protected Base64Encoder getBase64Encoder() {
        return Base64EncoderImpl.getInstance();
    }
}
